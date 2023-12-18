package javafx.utils.components.spinner;

import io.github.palexdev.materialfx.controls.models.spinner.SpinnerModel;
import javafx.beans.property.*;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class IntegerSpinnerModel implements SpinnerModel<Integer> {

    private IntegerProperty actual;
    private Integer min;
    private Integer max;
    private Integer step;

    private IntegerStringConverter converter = new IntegerStringConverter();

    public IntegerSpinnerModel(Integer min, Integer max, int step) {
        this.min = min != null ? min : Integer.MIN_VALUE;
        this.max = max != null ? max : Integer.MAX_VALUE;
        this.step = step;
        actual = new SimpleIntegerProperty();
        setActual(min, max);
    }

    private void setActual(Integer min, Integer max) {
        setValue(min != null ? min : max != null ? max : 0);
    }

    /**
     *
     */
    @Override
    public void next() {

        int next = actual.get() + step;
        if (max != null && max - next < 0)
            setValue(max);
        else
            setValue(next);
    }

    /**
     *
     */
    @Override
    public void previous() {

        int previous = actual.get() - step;
        if (min != null && min - previous > 0)
            setValue(min);
        else
            setValue(previous);

    }

    /**
     *
     */
    @Override
    public void reset() {
        setActual(min, max);
    }

    /**
     * @return
     */
    @Override
    public Integer getValue() {
        return actual.get();
    }

    /**
     * @return
     */
    @Override
    public ObjectProperty<Integer> valueProperty() {
        return actual.asObject();
    }

    /**
     * @param aInteger
     */
    @Override
    public void setValue(Integer aInteger) {
        actual.setValue(aInteger);
    }

    /**
     * @return
     */
    @Override
    public StringConverter<Integer> getConverter() {
        return converter;
    }

    /**
     * @return
     */
    @Override
    public ObjectProperty<StringConverter<Integer>> converterProperty() {
        return new SimpleObjectProperty<>(converter);
    }

    /**
     * @param stringConverter
     */
    @Override
    public void setConverter(StringConverter<Integer> stringConverter) {

    }

    /**
     * @return
     */
    @Override
    public boolean isWrapAround() {
        return false;
    }

    /**
     * @param b
     */
    @Override
    public void setWrapAround(boolean b) {

    }
}
