package repository;

import domain.Tema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TemaXMLRepo extends AbstractXMLRepository<String, Tema> {

    /**
     * Class constructor
     * @param filename - The name of the file
     */
    public TemaXMLRepo(String filename){
        super(filename);
    }

    /**
     * Extract info about hte homework from the XML enetity
     * @param element - The element that contains the information about the homework
     * @return homework
     */
    @Override
    public Tema extractEntity(Element element) {
        String nrTema = element.getAttribute("nrTema");
        NodeList nods = element.getChildNodes();
        String descriere =element.getElementsByTagName("descriere")
                .item(0)
                .getTextContent();
        String deadline =element.getElementsByTagName("deadline")
                .item(0)
                .getTextContent();
        String primire =element.getElementsByTagName("primire")
                .item(0)
                .getTextContent();

        return new Tema(nrTema, descriere, Integer.parseInt(deadline), Integer.parseInt(primire));
    }

    /**
     * Creates an XML element from a homework entity
     * @param document
     * @param entity
     * @return
     */
    @Override
    public Element createElementfromEntity(Document document, Tema entity) {
        Element e = document.createElement("nrTema");
        e.setAttribute("nrTema", entity.getID());

        Element descriere = document.createElement("descriere");
        descriere.setTextContent(entity.getDescriere());
        e.appendChild(descriere);

        Element deadline = document.createElement("deadline");
        Integer g=entity.getDeadline();
        deadline.setTextContent(g.toString());
        e.appendChild(deadline);

        Element primire = document.createElement("primire");
        Integer p=entity.getPrimire();
        primire.setTextContent(p.toString());
        e.appendChild(primire);

        return e;
    }
}
