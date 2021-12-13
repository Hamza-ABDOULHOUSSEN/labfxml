# Projet PCD Album photo

* [sujet du projet](Album Photo.pdf)
* Auteur : ABDOULHOUSSEN Hamza

## Option de l'Album

### Charger/Restaurer une sauvegarde

#### Les dossiers de sauvegarde
Pour les sauvegardes il faut séléctionner un dossier contenant un fichier `save.txt`  
Par exemple les dossiers dans labfxml-abdoulho1u/Album/src/main/resources/Saves  
Il est possible de creer une nouvelle sauvegarde en creant un nouveau dossier avec un `save.txt` ou en copiant un dossier déjà existant

#### Boutons de sauvegarde
Il est possible de charger une sauvegarde.
- Le bouton `Save As` permet de choisir le dossier de sauvegarde
- Le bouton `Save` permet de sauvegarder dans la sauvegarde courante ou d'en séléctionner s'il n'y en a pas encore

#### Boutons de Restauration
Il est possible de restaurer une sauvegarde.
- Le bouton `Restore From` permet de choisir le dossier de sauvegarde
- Le bouton `Restore` permet de sauvegarder depuis la sauvegarde courante ou d'en séléctionner s'il n'y en a pas encore

### Ajouter des photos à l'inventaire
Le bouton `ADD PHOTOS` permet de charger un dossier contenant plusieurs images  
Par exemple le dossier labfxml-abdoulho1u/Album/src/main/resources/Images/Cat
Attention, les images ne sont pas copiées. Si les images du dossier sont supprimés, l'Album ne les charge plus  

### Modifier le titre d'une image dans la double page
Il faut cliquer sur le titre puis écrire le nouveau titre souhaité  
En inserant une image, le titre devient automatiquement celui de l'image, sauf si le titre a été modifié avant  

### Inserer une image dans la double page
Pour inserer une image dans la double page, il est possible de faire :
- soit un clique droit sur une image et inserer à droite ou à gauche
- soit de prendre l'image depuis l'inventaire jusqu'à la page (drag and drop)

### Quitter l'Album
Lorsque l'Album est quitté, si aucune sauvegarde n'est chargé ou que l'Album ne correspond pas à la derniere sauvegarde. L'Album demande s'il l'on veut sauvegarder avant de quitter

## Lancement du jar

Le ficheir `launch_jar.sh` contient la commande permettant d'éxécuter le fichier jar
Pour lander le jar, il faut éxécuter la commande
```
launch_jar.sh
```

## Modele et diagramme

Le modele est la classe Album qui extends de SujetObserve. On peut donc ajouter des observateurs et notifier des observateurs depuis la classe Album.  

Les controlleurs sont dans le package Controller. Ce sont des observateurs, ils ont tous un attribut album de type Album et une méthode update qui correspond à l'action effectuée lors de la notification des observateurs.  

Les images de l'inventaire ne sont pas mit à jour à chaque notification parce que cela pourrait prendre beaucoup de temps.




