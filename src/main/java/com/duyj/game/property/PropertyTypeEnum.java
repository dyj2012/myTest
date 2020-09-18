package com.duyj.game.property;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2020/7/8
 */
public enum PropertyTypeEnum {

    /**
     */
    CRIT("暴击"),
    DEFENSE("防御"),
    FORCE("攻击力"),
    LIFE("生命"),
    SPEED("速度"),
    LUCKY("运气"),
    LEVEL("等级"),
    ;


    private final String title;
    private final String desc;


    PropertyTypeEnum(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    PropertyTypeEnum(String title) {
        this.title = title;
        this.desc = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
