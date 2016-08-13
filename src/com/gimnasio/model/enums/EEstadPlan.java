package com.gimnasio.model.enums;

/**
 * @author Eminson Mendoza Martinez
 */
public enum EEstadPlan {

    ACTIVO {
                @Override
                public String getNombre() {
                    return "SI";
                }

                @Override
                public short getId() {
                    return 1;
                }
            },
    VENCIDO {
                @Override
                public String getNombre() {
                    return "VENCIDO";
                }

                @Override
                public short getId() {
                    return 2;
                }
            };

    /**
     * @param val
     * @return
     */
    public static EEstadPlan getResult(short val) {
        for (EEstadPlan obj : EEstadPlan.getValues()) {
            if (obj.getId() == val) {
                return obj;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public static EEstadPlan[] getValues() {
        return EEstadPlan.values();
    }

    public abstract String getNombre();

    public abstract short getId();
}
