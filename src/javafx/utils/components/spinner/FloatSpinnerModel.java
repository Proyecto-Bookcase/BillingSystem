package javafx.utils.components.spinner;

import io.github.palexdev.materialfx.controls.models.spinner.SpinnerModel;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;

public class FloatSpinnerModel implements SpinnerModel<Float> {

    private FloatProperty actual;
    private Float min;
    private Float max;
    private Float step;

    private FloatStringConverter converter = new FloatStringConverter();

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
     * @return
     */
    @Override
    public Float getValue() {
        return actual.get();
    }

    /**
     * @return
     */
    @Override
    public ObjectProperty<Float> valueProperty() {
        return actual.asObject();
    }

    /**
     * @param aFloat
     */
    @Override
    public void setValue(Float aFloat) {
        actual.setValue(aFloat);
    }

    /**
     * @return
     */
    @Override
    public StringConverter<Float> getConverter() {
        return converter;
    }

    /**
     * @return
     */
    @Override
    public ObjectProperty<StringConverter<Float>> converterProperty() {
        return new SimpleObjectProperty<>(converter);
    }

    /**
     * @param stringConverter
     */
    @Override
    public void setConverter(StringConverter<Float> stringConverter) {

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
