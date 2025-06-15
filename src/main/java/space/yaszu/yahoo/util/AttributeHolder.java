package space.yaszu.yahoo.util;

import org.bukkit.attribute.Attribute;

public class AttributeHolder {
    public Attribute attribute;
    public double value;
    public AttributeHolder(Attribute attribute, double value) {
        this.value = value;
        this.attribute = attribute;
    }
}
