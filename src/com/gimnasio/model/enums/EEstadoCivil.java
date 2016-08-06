package com.gimnasio.model.enums;

/**
 * @author Eminson Mendoza Martinez
 */
public enum EEstadoCivil {

    SOLTERO {
                @Override
                public String getNombre() {
                    return "Soltero";
                }

                @Override
                public short getId() {
                    return 1;
                }
            },
    CASADO {
                @Override
                public String getNombre() {
                    return "Casado";
                }

                @Override
                public short getId() {
                    return 2;
                }
            },
    VIUDO {
                @Override
                public String getNombre() {
                    return "Viudo";
                }

                @Override
                public short getId() {
                    return 3;
                }
            },
    DIVORCIADO {
                @Override
                public String getNombre() {
                    return "Divorciado";
                }

                @Override
                public short getId() {
                    return 4;
                }
            };

    /**
     * @param val
     * @return
     */
    public static EEstadoCivil getResult(short val) {
        for (EEstadoCivil obj : EEstadoCivil.getValues()) {
            if (obj.getId() == val) {
                return obj;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public static EEstadoCivil[] getValues() {
        return EEstadoCivil.values();
    }

    public abstract String getNombre();

    public abstract short getId();
}
