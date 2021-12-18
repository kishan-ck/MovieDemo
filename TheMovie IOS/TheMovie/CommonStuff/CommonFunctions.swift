//
//  CommonFunctions.swift
//  TheMovie
//
//  Created by iMac on 16/12/21.
//

import Foundation
import Alamofire
import CRNotifications

//MARK: - Check Internet Connectivity
///Checks Internet Connectivity
struct Connectivity {
    static let sharedInstance = NetworkReachabilityManager()!
    static var isConnectedToInternet:Bool {
        return self.sharedInstance.isReachable
    }
}


/// Inherited from MeterialComponents for making toast message
func makeToast(type: CRNotificationType , title: String, message: String) {
    CRNotifications.showNotification(type: type, title: title.capitalized, message: message.capitalized, dismissDelay: 3)
}
/// Getting valu back
typealias backDataHandler = (Any) -> Void
