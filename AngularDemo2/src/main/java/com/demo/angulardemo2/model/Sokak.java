package com.demo.angulardemo2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "sokak")
@XmlRootElement
public class Sokak implements Serializable
{

   /**
    * 
    */
   private static final long serialVersionUID = 6011181701184901597L;
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String ad;

   @ManyToOne
   private Mahalle mahalle;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Sokak))
      {
         return false;
      }
      Sokak other = (Sokak) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getAd()
   {
      return ad;
   }

   public void setAd(String ad)
   {
      this.ad = ad;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      if (ad != null && !ad.trim().isEmpty())
         result += ", ad: " + ad;
      return result;
   }

   public Mahalle getMahalle()
   {
      return this.mahalle;
   }

   public void setMahalle(final Mahalle mahalle)
   {
      this.mahalle = mahalle;
   }
}