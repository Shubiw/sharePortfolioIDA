/*
 * Copyright 2024 mro72.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendreTest {

    @Test
    public void testVendreQuantiteSuperieureAuStock() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple action = new ActionSimple("NomAction");
        int quantiteAVendre = 15;

        portefeuille.acheter(action, 10); // Achetez 10 actions

        // Utilisez la méthode vendre pour tester la vente de quantité supérieure au stock
        assertThrows(IllegalArgumentException.class, () -> {
            portefeuille.vendre(action, quantiteAVendre);
        });

        // Vous pouvez également vérifier d'autres choses après l'appel de la méthode vendre
        // Par exemple, vérifier que le stock est toujours égal à 10
        assertEquals(10, portefeuille.mapLignes.get(action).getQte());
    }

    // Ajoutez d'autres tests au besoin
}

