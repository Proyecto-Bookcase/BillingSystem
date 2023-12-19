package javafx.utils.components.spinner;

import io.github.palexdev.materialfx.controls.models.spinner.SpinnerModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class IntegerSpinnerModel implements SpinnerModel<Integer> {

    private final IntegerProperty actual;
    private final Integer min;
    private final Integer max;
    private final Integer step;

    private final IntegerStringConverter converter = new IntegerStringConverter();

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
     *
     */
    @Override
    public Integer getValue() {
        return actual.get();
    }

    /**
     *
     */
    @Override
    public ObjectProperty<Integer> valueProperty() {
        return actual.asObject();
    }

    /**
     *
     */
    @Override
    public void setValue(Integer aInteger) {
        actual.setValue(aInteger);
    }

    /**
     *
     */
    @Override
    public StringConverter<Integer> getConverter() {
        return converter;
    }

    /**
     *
     */
    @Override
    public ObjectProperty<StringConverter<Integer>> converterProperty() {
        return new SimpleObjectProperty<>(converter);
    }

    /**
     *
     */
    @Override
    public void setConverter(StringConverter<Integer> stringConverter) {

    }

    /**
     *
     */
    @Override
    public boolean isWrapAround() {
        return false;
    }

    /**
     *
     */
    @Override
    public void setWrapAround(boolean b) {

    }
}
