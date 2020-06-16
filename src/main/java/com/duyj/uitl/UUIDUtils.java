package com.duyj.uitl;

import java.util.UUID;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/07/26
 */
public class UUIDUtils {
    /**
     * 获得32位的UUID
     *
     * @return
     */
    public synchronized static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
