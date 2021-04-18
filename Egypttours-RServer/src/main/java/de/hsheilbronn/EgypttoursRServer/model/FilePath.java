/**
 *  Copyright (C) 2021  the original author or authors.
 *
 * 		This program is free software: you can redistribute it and/or modify
 * 		it under the terms of the GNU General Public License as published by
 * 		the Free Software Foundation, either version 3 of the License, or
 * 		(at your option) any later version.
 *
 * 		This program is distributed in the hope that it will be useful,
 * 		but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 		GNU General Public License for more details.
 *
 * 		You should have received a copy of the GNU General Public License
 * 		along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package de.hsheilbronn.EgypttoursRServer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Entity
public class FilePath {

    @Id
    private String path;
    private String description;
    private String format;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilePath filePath = (FilePath) o;
        return Objects.equals(path, filePath.path) && Objects.equals(description, filePath.description) && Objects.equals(format, filePath.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, description, format);
    }
}
