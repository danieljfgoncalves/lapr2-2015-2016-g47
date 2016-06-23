/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an application analysis
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationAnalysis {

    /**
     * The application.
     */
    private final Application application;

    /**
     * List with the average of each answer.
     */
    private final List<Float> answersAverage;

    /**
     * The total average of all questions averages.
     */
    private final float totalAverage;

    /**
     * Creates the applicaton analysis.
     *
     * @param application Application to be analysed
     */
    public ApplicationAnalysis(Application application) {
        this.application = application;
        this.answersAverage = calculateAverages();
        this.totalAverage = calculateTotalAverage();
    }

    /**
     * Calculates the average of each answer.
     *
     * @return list with the average of each answer
     */
    private List<Float> calculateAverages() {
        List<Float> answersMean = new ArrayList<>();

        List<Evaluation> evaluations = this.application.getEvaluationsList();

        int rows = evaluations.size();
        int cols = evaluations.get(0).getAnswersList().size();

        int[][] answers = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            List<Integer> evaluationAnswers = evaluations.get(i).getAnswersList();
            for (int j = 0; j < cols; j++) {
                answers[i][j] = evaluationAnswers.get(j);
            }
        }

        for (int j = 0; j < cols; j++) {
            int sum = 0;
            for (int i = 0; i < rows; i++) {
                sum += answers[i][j];
            }
            float average = (float) sum / rows;
            answersMean.add(average);
        }

        return answersMean;
    }

    /**
     * Calculates the total average of all questions averages.
     *
     * @return total average of all questions averages
     */
    private float calculateTotalAverage() {
        float sum = 0;
        for (Float value : this.answersAverage) {
            sum += value;
        }
        return (float) sum / this.answersAverage.size();
    }

    /**
     * Gets the answers average.
     *
     * @return answers averages list
     */
    public List<Float> getAnswersAverage() {
        return answersAverage;
    }

    /**
     * Gets the total average of all questions averages.
     *
     * @return total average of all questions averages
     */
    public float getTotalAverage() {
        return totalAverage;
    }

    /**
     * Gets the display info of the application.
     *
     * @return display info.
     */
    public String getApplicationDisplayInfo() {
        return ((Selectable) this.application).getDisplayInfo();
    }

    /**
     * Return the textual representation of the application analysis.
     *
     * @return the textual representation of the application analysis
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%-15s", "Application"));
        for (int i = 0; i < this.answersAverage.size(); i++) {
            s.append(String.format("%-10s %d", "Answer", i));
        }
        s.append(String.format("%n"));
        
        s.append(String.format("%15s", this.getApplicationDisplayInfo()));
        for (int i = 0; i < this.answersAverage.size(); i++) {
            s.append(String.format("%-10f", answersAverage.get(i)));
        }
        s.append(String.format("%n"));
        
        return s.toString();
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it repreents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        ApplicationAnalysis otherApplicationAnalysis = (ApplicationAnalysis) otherObject;

        return this.application.equals(otherApplicationAnalysis.application)
                && this.answersAverage.equals(otherApplicationAnalysis.answersAverage)
                && this.totalAverage == otherApplicationAnalysis.totalAverage;
    }

}