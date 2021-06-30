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

package de.hsheilbronn.EgypttoursRServer.dto;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
public class AdresseDTO {

    private String id;
    private String stadt;
    private String strasse;
    private String xKoordinate;
    private String yKoordinate;
    private String alternative;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id != null && !id.isEmpty() )
        this.id = id;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        if(stadt != null && !stadt.isEmpty() )
        this.stadt = stadt;
    }

    public String getStrasse() {
            return strasse;
    }

    public void setStrasse(String strasse) {
        if(strasse != null && !strasse.isEmpty() )
            this.strasse = strasse;
    }

    public String getxKoordinate() {
        return xKoordinate;
    }

    public void setxKoordinate(String xKoordinate) {
        if(xKoordinate != null && !xKoordinate.isEmpty())
            this.xKoordinate = xKoordinate;
    }

    public String getyKoordinate() {
        return yKoordinate;
    }

    public void setyKoordinate(String yKoordinate) {
        if(yKoordinate != null && !yKoordinate.isEmpty())
            this.yKoordinate = yKoordinate;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        if(alternative != null && !alternative.isEmpty())
            this.alternative = alternative;
    }
}
