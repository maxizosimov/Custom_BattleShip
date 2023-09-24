# Changes in PA03 files made for PA04
- Changed the logic of tracking the AI shots and then checking if a current shot already exists in a list of fired shots
to cover overlapping of shots / eliminate duplicate shots
- Handle APlayer to delegate the updating of ships remaining to the AIPlayer instead of both
in order to decouple the code so that we could update the correct amount of shots for the player
- Changed iteration from - i to + i because it messed up the server as the server takes placement of idx 0
meanwhile negative iteration made the server retrieve last coordinate of a ship even though it was our start
- Changed logic of placing ships as it was flawed before, overlapping coordinates and 1 idx based which messed the 
server up as setup would always be invalid
- Handling of shooting out of bounds as that was an issue before so that the server could process the players shot