package validation;

import domain.Tema;

public class TemaValidator implements Validator<Tema> {

    /**
     * Validate a homework
     * @param entity - homework to be validated
     * @throws ValidationException if the homework isn't valid
     */
    @Override
    public void validate(Tema entity) throws ValidationException {
        if(entity.getID().equals("") || entity.getID() == null) {
            throw new ValidationException("The number of the homework is not valid!");
        }
        if(entity.getDescriere().equals("")){
            throw new ValidationException("Invalid Description!");
        }
        if(entity.getDeadline() < 1 || entity.getDeadline() > 14) {
            throw new ValidationException("Deadline should between 1-14");
        }
        if(entity.getPrimire() < 1 || entity.getPrimire() > 14) {
            throw new ValidationException("Week given should be between 1-12.");
        }
    }
}
