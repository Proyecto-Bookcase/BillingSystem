package javafx.utils.components.spinner;

import io.github.palexdev.materialfx.controls.models.spinner.SpinnerModel;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;

public class FloatSpinnerModel implements SpinnerModel<Float> {

    private final FloatProperty actual;
    private final Float min;
    private final Float max;
    private final Float step;

    private final FloatStringConverter converter = new FloatStringConverter();

    public FloatSpinnerModel(Float min, Float max, float step) {
        this.min = min != null ? min : Float.MIN_VALUE;
        this.max = max != null ? max : Float.MAX_VALUE;
        this.step = step;
        actual = new SimpleFloatProperty();
        setActual(min, max);
    }

    private void setActual(Float min, Float max) {
        setValue(min != null ? min : max != null ? max : 0);
    }

    /**
     *
     */
    @Override
    public void next() {

        float next = actual.get() + step;
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

        float previous = actual.get() - step;
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
    public Float getValue() {
        return actual.get();
    }

    /**
     *
     */
    @Override
    public ObjectProperty<Float> valueProperty() {
        return actual.asObject();
    }

    /**
     *
     */
    @Override
    public void setValue(Float aFloat) {
        actual.setValue(aFloat);
    }

    /**
     *
     */
    @Override
    public StringConverter<Float> getConverter() {
        return converter;
    }

    /**
     *
     */
    @Override
    public ObjectProperty<StringConverter<Float>> converterProperty() {
        return new SimpleObjectProperty<>(converter);
    }

    /**
     *
     */
    @Override
    public void setConverter(StringConverter<Float> stringConverter) {

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
