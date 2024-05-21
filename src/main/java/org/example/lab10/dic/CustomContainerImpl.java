package org.example.lab10.dic;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.example.lab10.dic.utils.InstanceNotFoundException;
import org.example.lab10.dic.utils.InvalidException;
import org.example.lab10.dic.utils.NullException;
import org.example.lab10.dic.utils.RedeclaredException;

@SuppressWarnings("unchecked")
public class CustomContainerImpl implements CustomContainer {
    private final Map<String, Object> instances = new HashMap<>();
    private final Map<Class<?>, Function<CustomContainer, ?>> factory = new HashMap<>();

    @Override
    public <T> boolean addInstance(T instance) {
        if (instance == null) {
            throw new NullException("Null is not allowed as a parameter");
        }
        String typeName = String.valueOf(instance.getClass());
        return addInstance(instance, typeName);
    }

    @Override
    public <T> boolean addInstance(T instance, String customName) {
        if (instance == null || customName == null) {
            throw new NullException("Null is not allowed as a parameter");
        }
        if (instances.containsKey(customName)) {
            throw new RedeclaredException("Instances cannot be redeclared");
        }
        instances.put(customName, instance);
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        if (type == null) {
            throw new NullException("Null is not allowed as a parameter");
        }
        for (Object instance : instances.values()) {
            if (type.isInstance(instance)) {
                return type.cast(instance);
            }
        }
        if (factory.containsKey(type)) {
            T newInstance = (T) factory.get(type).apply(this);
            addInstance(newInstance, type.getName());
            return newInstance;
        }

        throw new InstanceNotFoundException("Cannot provide instance");
    }

    @Override
    public <T> T getInstance(Class<T> type, String customName) {
        if (type == null || customName == null) {
            throw new NullException("Null is not allowed as a parameter");
        }
        if (instances.containsKey(customName)) {
            Object instance = instances.get(customName);
            if (!type.isInstance(instance)) {
                throw new InvalidException("Invalid type for object");
            }
            return type.cast(instance);
        } else {
            if (factory.get(type) != null) {
                T newInstance = (T) factory.get(type).apply(this);
                addInstance(newInstance, customName);
                return newInstance;
            }
        }
        throw new InstanceNotFoundException("Cannot provide instance");
    }

    @Override
    public <T> boolean addFactoryMethod(Class<T> type, Function<CustomContainer, T> factoryMethod) {
        if (type == null || factoryMethod == null) {
            throw new NullException("Null is not allowed as a parameter");
        }
        factory.put(type, factoryMethod);
        return true;
    }

    @Override
    public <T> T create(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Null is not allowed as a parameter");
        }

        if (!factory.containsKey(type)) {
            throw new IllegalStateException("Cannot provide instance");
        }
        T newInstance = (T) factory.get(type).apply(this);

        if (newInstance != null) {
            return newInstance;
        }

        throw new IllegalStateException("Cannot provide instance");
    }

    @Override
    public <T> T create(Class<T> type, Map<String, Object> parameters) {
        if (type == null) {
            throw new IllegalArgumentException("Null is not allowed as a parameter");
        }

        if (!factory.containsKey(type)) {
            throw new IllegalStateException("Cannot provide instance");
        }

        if (parameters == null || parameters.isEmpty()) {
            T newInstance = (T) factory.get(type).apply(this);
            if (newInstance != null) {
                return newInstance;
            }
        } else {
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                addInstance(entry.getValue(), entry.getKey());
            }
            T newInstance = (T) factory.get(type).apply(this);
            if (newInstance != null) {
                return newInstance;
            }
        }

        throw new IllegalStateException("Cannot provide instance");
    }

    @Override
    public void close() throws Exception {
        for (Object instance : instances.values()) {
            if (instance instanceof Closeable) {
                ((Closeable) instance).close();
            }
        }
        instances.clear();
    }
}
