package business_logic.repository;

import java.util.Date;

/**
 * API for Repository
 * @author Adrien
 */
public abstract class Repository extends Follow {

    abstract public String getDescription();
    abstract public void setDescription(String value);

    abstract public Date getCreatedAt();
    abstract public void setCreatedAt(Date value);

    abstract public Date getUpdatedAt();
    abstract public void setUpdatedAt(Date value);
}
