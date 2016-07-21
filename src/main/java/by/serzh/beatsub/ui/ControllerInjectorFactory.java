package by.serzh.beatsub.ui;

import com.gluonhq.ignite.guice.GuiceContext;
import javafx.util.Callback;

public class ControllerInjectorFactory implements Callback<Class<?>, Object> {

    private static ControllerInjectorFactory instance;
    private final GuiceContext context;

    private ControllerInjectorFactory(GuiceContext context) {
        this.context = context;
    }


    public static ControllerInjectorFactory getInstance() {
        return instance;
    }

    public static synchronized void init(GuiceContext context) {
        instance = new ControllerInjectorFactory(context);
    }

    @Override
    public Object call(Class<?> type) {
        if(context == null) {
            throw new IllegalStateException("init() must have been called");
        }
        return context.getInstance(type);
    }
}