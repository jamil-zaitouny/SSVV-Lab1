package ssvv.repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public abstract class AbstractXMLRepository<ID, E extends HasID<ID>> extends AbstractCrudRepository<ID, E> implements FileRepository<ID, E> {

    private String filename;
    private DocumentBuilderFactory builderFactory;

    /**
     * Class constructor
     * @param filename - the name of the file
     */
    AbstractXMLRepository(String filename) {
        this.filename = filename;
        builderFactory=DocumentBuilderFactory.newInstance();
        loadFromFile();

    }


    /**
     * Load the data from the file
     */
    public void loadFromFile(){
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(this.filename);

            Element root = document.getDocumentElement();
            NodeList children = root.getChildNodes();
            for(int i=0; i < children.getLength(); i++) {
                Node entityElement = children.item(i);
                if(entityElement.getNodeType()==Node.ELEMENT_NODE) {
                    E entity = extractEntity((Element)entityElement);
                    super.save(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates an XML element from an entity
     * @param document
     * @param entity
     * @return
     */

    public abstract Element createElementfromEntity(Document document, E entity);

    /**
     * Extract an object from an XML entity
     * @param element - The string for which the object is to be extracted from
     * @return - the object
     */


    public abstract E extractEntity(Element element);
    /**
     * Saves an object
     * @param entity - The object to be saved
     * @return Null if the object was saved successfully, false otherwise
     */
   @Override
    public E save(E entity) {
       E entity1 = super.save(entity);
       if (entity1 == null) {
            //saveToFile(entity);
       writeToFile();
       }

        return entity1;
   }
    /**
     * Rewrite the file with all the objects from memory
     */

    public void writeToFile(){
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);
            super.findAll().forEach(e->{
                Element elem = createElementfromEntity(document,e);
                root.appendChild(elem);
            });

            //write Document to file
            Transformer transformer = TransformerFactory.
                    newInstance().newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult(this.filename));

        }catch(Exception e){
        e.printStackTrace();
        }
    }

    /**
     * Write a new object in the file
     * @param entity - The object to be writte
     */
    public void saveToFile(E entity)
    {
        //E e = super.save(entity);
        //if(e==null){writeToFile();
        //}
        //return e;
    }


    /**
     * Delete an object
     * @param id - The id of the object
     * @return the object if the deletion was successful or null if the object does not exist
     */
    @Override
    public E delete(ID id) {
        E entity = super.delete(id);
        if(entity != null){
            writeToFile();
        }
        return entity;
    }

    /**
     * Modify an object
     * @param entity - The new object
     * @return null if the object has been modified or the object, if it does not exist
     */
    @Override
    public E update(E entity) {
        E entity1 = super.update(entity);
        //@Todo doesn't make sense to check if the entity is null or not, only entity1 makes sense
        //        if(entity1 == null){
        if(entity == null){
            writeToFile();
        }
        return entity1;
    }
}
