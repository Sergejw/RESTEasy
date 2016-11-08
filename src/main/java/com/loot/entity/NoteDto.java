package com.loot.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *	This class represents an note entity. Note entities are simple notes, which 
 * 	@author XRG
 */
@Entity
@Table(name = "note")
public class NoteDto implements Serializable {

	/** Serialization version number. */
    private static final long serialVersionUID = 1L;
    
    /** Unique note number/identifier. */
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    /** Content of the note. */
    @Column(name = "text")
    private String text;
    
    /** Time stamp, when the note was created. */
    @Column(name = "date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    /**
     * 	Initializes the note entity with text and date. Text contains as value the 
     * 	content of the note. Date is the time stamp, when the note was created. 
     * 	@param text Note content.
     * 	@param date Time stamp. 
     */
    public NoteDto(long id, String text, Date date) {
    	this.id = id;
        this.text = text;
        this.date = date;
    }
    
    /** Default constructor for JPA frameworks like hibernate. */
    public NoteDto() {}
    
    /**
     * 	Returns unique note number/identifier.
     * 	@return Unique note number/identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * 	Updates unique note number/identifier.
     * 	@param id New unique note number/identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 	Returns content/value of the note entity.
     * 	@return Note content.
     */
    public String getText() {
        return text;
    }

    /**
     * 	Updates content/value of the note entity.
     * 	@param text New content/value of the note entity.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 	Returns time stamp/date of the note.
     * 	@return Time stamp.
     */
    public Date getDate() {
        return date;
    }

    /**
     * 	Updates time stamp of the note entity.
     * 	@param date New time stamp.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}