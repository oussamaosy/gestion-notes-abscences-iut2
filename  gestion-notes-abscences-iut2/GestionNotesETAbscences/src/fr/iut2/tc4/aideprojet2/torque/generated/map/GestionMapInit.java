package fr.iut2.tc4.aideprojet2.torque.generated.map;

import org.apache.torque.TorqueException;

/**
 * This is a Torque Generated class that is used to load all database map 
 * information at once.  This is useful because Torque's default behaviour
 * is to do a "lazy" load of mapping information, e.g. loading it only
 * when it is needed.<p>
 *
 * @see org.apache.torque.map.DatabaseMap#initialize() DatabaseMap.initialize() 
 */
public class GestionMapInit
{
    public static final void init()
        throws TorqueException
    {
        fr.iut2.tc4.aideprojet2.torque.generated.GroupePeer.getMapBuilder();
        fr.iut2.tc4.aideprojet2.torque.generated.EtudiantPeer.getMapBuilder();
        fr.iut2.tc4.aideprojet2.torque.generated.MatierePeer.getMapBuilder();
        fr.iut2.tc4.aideprojet2.torque.generated.AbsencePeer.getMapBuilder();
        fr.iut2.tc4.aideprojet2.torque.generated.NotePeer.getMapBuilder();
    }
}
