/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pets;

import java.time.LocalDateTime;

/**
 *
 * @author rdash
 */
public interface LicensedPet {
    Boolean isLicensed();
    void assignLicense();
    LocalDateTime whenLicensed();
    
    static void test() {
        
    }
}
