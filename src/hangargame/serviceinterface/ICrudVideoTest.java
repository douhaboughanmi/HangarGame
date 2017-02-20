
package hangargame.serviceinterface;

import hangargame.entites.VideoTest;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author DELL
 */
public interface ICrudVideoTest {

    public void ajouter(VideoTest v);

    public void supprimer(int id);

    public void modifier(VideoTest v);

    public List<VideoTest> afficher();

}
