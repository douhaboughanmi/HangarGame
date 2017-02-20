
package hangargame.serviceinterface;

import hangargame.entites.VideoEnDirect;
import hangargame.entites.VideoTest;
import java.util.List;



/**
 *
 * @author DELL
 */
public interface ICrudVideoEnDirect {
    
      public void ajouter(VideoEnDirect v);

    public void supprimer(int id);

    public void modifier(VideoEnDirect v);

    public List<VideoEnDirect> afficher();

    
}
