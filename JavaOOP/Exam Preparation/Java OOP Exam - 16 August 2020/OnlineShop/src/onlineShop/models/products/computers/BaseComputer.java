package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private Map<String, Component> components;
    private Map<String, Peripheral> peripherals;


    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();
    }

    @Override
    public List<Component> getComponents() {
        return (List<Component>) this.components.values();
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return (List<Peripheral>) this.peripherals.values();
    }

    @Override
    public void addComponent(Component component) {
        if (components.containsKey(component.getClass().getSimpleName())) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT,
                    component.getClass().getSimpleName(), getClass().getSimpleName(), component.getId()));
        }
        components.put(component.getClass().getSimpleName(),component);
    }

    @Override
    public Component removeComponent(String componentType) {
        if (components.isEmpty() || !components.containsKey(componentType)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType, getClass().getSimpleName(), getId()));
        }
        return components.remove(componentType);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.containsKey(peripheral.getClass().getSimpleName())) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName()
            , getClass().getSimpleName(), peripheral.getId()));
        }
        peripherals.put(peripheral.getClass().getSimpleName(), peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (peripherals.isEmpty() || !peripherals.containsKey(peripheralType)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL,
                    peripheralType, getClass().getSimpleName(), getId()));
        }
        return peripherals.remove(peripheralType);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)%n",getOverallPerformance()
                ,getPrice(), getClass().getSimpleName(), getManufacturer(), getModel(),getId()));
        builder.append(String.format(" Components (%d):",components.size())).append(System.lineSeparator());
        for (Component c : components.values()) {
            builder.append(String.format("  %s",c.toString()));
        }
        double averageOverallPerformance = peripherals.values().stream().mapToDouble(Peripheral::getOverallPerformance).sum();

        builder.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):",
                peripherals.size(), averageOverallPerformance )).append(System.lineSeparator());
        for (Peripheral pName : peripherals.values()) {
            builder.append(String.format("  %s",pName.toString())).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return this.overallPerformance;
        }
        double current = components.values().stream().mapToDouble(Component::getOverallPerformance).sum();
        return overallPerformance + (current / components.size());
    }

    @Override
    public double getPrice() {
        double computerPrice = super.getPrice();
        double componentsPrice = components.values().stream().mapToDouble(Component::getPrice).sum();
        double peripheralsPrice = peripherals.values().stream().mapToDouble(Peripheral::getPrice).sum();
        double totalSum = componentsPrice + computerPrice + peripheralsPrice;
        return totalSum;
    }
}
