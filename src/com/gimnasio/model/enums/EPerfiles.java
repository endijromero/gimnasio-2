package com.gimnasio.model.enums;

/**
 *
 * @author emimaster16
 */
public enum EPerfiles {

    ADMIN {
                @Override
                public String getNombre() {
                    return "Admin";
                }

                @Override
                public short getId() {
                    return 1;
                }
            },
    FISIOTERAPEUTA {
                @Override
                public String getNombre() {
                    return "Fisioterapeuta";
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
    public static EPerfiles getResult(short val) {
        for (EPerfiles obj : EPerfiles.getValues()) {
            if (obj.getId() == val) {
                return obj;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public static EPerfiles[] getValues() {
        return EPerfiles.values();
    }

    public abstract String getNombre();

    public abstract short getId();
}
