package de.nicolas.utils.actions;

import de.nicolas.utils.actors.DialogBox;

public class TypewriterAction extends SetTextAction {

    private float elapsedTime;
    private float characterPerSecond;

    public TypewriterAction(String textToDisplay) {
        super(textToDisplay);
        elapsedTime = 0;
        characterPerSecond = 30;
    }

    @Override
    public boolean act(float delta) {
        elapsedTime += delta;
        int numberOfCharacters = (int)(elapsedTime * characterPerSecond);
        if(numberOfCharacters > textToDisplay.length()){
            numberOfCharacters = textToDisplay.length();
        }
        String partialText = textToDisplay.substring(0, numberOfCharacters);
        DialogBox dialogBox = (DialogBox) target;
        dialogBox.setText(partialText);

        // Die Action ist beendet, wenn alle Buchstaben angezeigt sind
        return (numberOfCharacters >= textToDisplay.length());
    }
}
