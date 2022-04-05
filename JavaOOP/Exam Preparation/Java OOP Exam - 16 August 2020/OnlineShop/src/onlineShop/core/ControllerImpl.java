package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computers;
    private Map<Integer, Component> components;
    private Map<String, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computers.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = null;
        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        computers.put(id,computer);
        return String.format(OutputMessages.ADDED_COMPUTER,id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer,
                                String model, double price, double overallPerformance, String connectionType) {
        Computer computer = computers.get(computerId);
        Peripheral peripheral = peripherals.values().stream().filter(p -> (p.getId() == id)).findFirst().orElse(null);

        if (peripheral != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }
        Peripheral currentPeripheral = null;
        switch (peripheralType) {
            case "Headset":
                currentPeripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                currentPeripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                currentPeripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                currentPeripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }
        computer.addPeripheral(currentPeripheral);
        peripherals.put(peripheralType, currentPeripheral);
        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, currentPeripheral.getId(), computer.getId());
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = computers.values().stream().filter(c -> (c.getId() == computerId)).findFirst().orElse(null);
        Peripheral p = computer.removePeripheral(peripheralType);
        peripherals.remove(peripheralType, p);
        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, p.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer,
                               String model, double price, double overallPerformance, int generation) {
        if (components.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }
        Component component = null;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }
        Computer computer = computers.get(computerId);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        computer.addComponent(component);
        components.put(component.getId(), component);

        return String.format(OutputMessages.ADDED_COMPONENT, componentType, component.getId(), computer.getId());
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = computers.get(computerId);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Component component = computer.removeComponent(componentType);
        components.remove(component.getId());
        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = computers.get(id);
        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        computers.remove(computer.getId());
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> computerList = new ArrayList<>(computers.values());
       List<Computer> computerList1 = computerList
                .stream()
                .sorted((c1, c2) -> Double.compare(c2.getOverallPerformance(), c1.getOverallPerformance()))
               .collect(Collectors.toList());
       Computer computer = computerList1.stream().filter(c -> (c.getPrice() <= budget)).findFirst().orElse(null);
       if (computer == null) {
           throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
       }
        computers.remove(computer.getId());
        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = computers.get(id);
        return computer.toString();
    }
}
