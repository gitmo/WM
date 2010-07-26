package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class GroupMatch extends Match
{
    public GroupMatch() {
    	super();
    }
    
    public String toString() {
    	return super.toString();
    }
}
