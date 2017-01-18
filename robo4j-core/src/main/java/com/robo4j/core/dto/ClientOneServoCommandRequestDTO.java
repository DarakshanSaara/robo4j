/*
 * Copyright (C) 2017. Miroslav Wengner, Marcus Hirt
 * This ClientServoCommandRequestDTO.java  is part of robo4j.
 * module: robo4j-core
 *
 * robo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * robo4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with robo4j .  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robo4j.core.dto;

import com.robo4j.commons.command.OneServoUnitCommandEnum;

/**
 * @author Miro Wengner (@miragemiko)
 * @since 18.01.2017
 */
public class ClientOneServoCommandRequestDTO implements ClientCommandDTO<OneServoUnitCommandEnum> {

    private Long stamp;
    private OneServoUnitCommandEnum command;
    private String value;
    private String speed;

    public ClientOneServoCommandRequestDTO(OneServoUnitCommandEnum command, String value) {
        this.stamp = System.currentTimeMillis();
        this.command = command;
        this.value = value;
        this.speed = "200";
    }

    public String getSpeed() {
        return speed;
    }

    @Override
    public OneServoUnitCommandEnum getCommand() {
        return command;
    }

    @Override
    public String getValue() {
        return value;
    }
}
