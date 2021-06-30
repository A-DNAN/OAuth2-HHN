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

import de.hsheilbronn.EgypttoursRServer.model.Adresse;
import de.hsheilbronn.EgypttoursRServer.model.FilePath;
import de.hsheilbronn.EgypttoursRServer.model.Oeffnungszeiten;

import java.util.List;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
public class AltaegyptischeStaetteDTO {


    private String id;
    private String name;
    private String beschreibung;
    private String geldBetrag;
    private String website;
    private String typeName;
    private List<String> bezahlarts;
    private String waehrung;
    private List<ReviewDTO> reviews;

    private Boolean preferredBy;



    private String erbauJahr;

    private List<FilePath> pictureUrls;
    private Adresse adresse;
    private List<Oeffnungszeiten> oeffnungszeiten;

    private String username;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getGeldBetrag() {
        return geldBetrag;
    }

    public void setGeldBetrag(String geldBetrag) {
        this.geldBetrag = geldBetrag;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> getBezahlarts() {
        return bezahlarts;
    }

    public void setBezahlarts(List<String> bezahlarts) {
        this.bezahlarts = bezahlarts;
    }

    public String getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(String waehrung) {
        this.waehrung = waehrung;
    }

    public String getErbauJahr() {
        return erbauJahr;
    }

    public void setErbauJahr(String erbauJahr) {
        this.erbauJahr = erbauJahr;
    }

    public List<FilePath> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<FilePath> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Oeffnungszeiten> getOeffnungszeiten() {
        return oeffnungszeiten;
    }

    public void setOeffnungszeiten(List<Oeffnungszeiten> oeffnungszeiten) {
        this.oeffnungszeiten = oeffnungszeiten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }


    public Boolean getPreferredBy() {
        return preferredBy;
    }

    public void setPreferredBy(Boolean preferredBy) {
        this.preferredBy = preferredBy;
    }
}
