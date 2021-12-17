//
//  API.swift
//  Event
//
//  Created by iMac on 26/10/21.
//  Copyright © 2021 CoderKube. All rights reserved.
//

import UIKit
import Alamofire
import CRNotifications

///THIS IS JSON Dictionary format
typealias JSONDictionary = Dictionary<String, AnyObject>
typealias JSONStringDictionary = Dictionary<String, String>

///THIS IS JSON array format
typealias JSONArray = Array<AnyObject>


/// Configaration for base url
struct Config{
    static let BaseUrl = "https://api.themoviedb.org/3"
    static let APIKey = "c9856d0cb57c3f14bf75bdc6c063b8f3"
  }


//MARK: - Class API
/// All API calls are goes from here so don't write any where else api calling code
class API: NSObject {
    
    //MARK: - Movie API
    /// getMovie API that will check for user is exist or not
    /// - Parameters:
    ///   - paramters:   var par = Parameters()
    ///                  par["api_key"] = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    ///   - result: provide movie data
    ///   - failure: failer will show the error string
    public func getMovie(paramters : Parameters ,result : @escaping ((_ responseObj : Movielist?) -> Void), failure : @escaping ((_ error : String?) -> Void)){

        CallService(serviceName : .movie, parameters : paramters, method : .get) { responseObj in
            if  let signIn = try? Movielist(from: responseObj as Any){
                result(signIn)
                makeToast(type: CRNotifications.success, title: "Success!" , message: "Moview list loaded")
            } else {
                failure("encoding problem")
            }
        } failure : { error in
            failure(error)
        }
    }
    
    public func getMovieDetails( movieid :String, paramters : Parameters ,result : @escaping ((_ responseObj : MovieDetails?) -> Void), failure : @escaping ((_ error : String?) -> Void)){

        CallService(serviceName : .moviedetails(movieid), parameters : paramters, method : .get) { responseObj in
            if  let signIn = try? MovieDetails(from: responseObj as Any){
                result(signIn)
             } else {
                failure("encoding problem")
            }
        } failure : { error in
            failure(error)
        }
    }
     
}


//MARK: - Call Services
/// Commonfunction taht is used to call all the APIs
func CallService(serviceName : APIEndPoint, parameters : Parameters, method : HTTPMethod, withSuccess : @escaping ((_ responseObj : JSONDictionary?) -> Void), failure : @escaping ((_ error : String?) -> Void)) {
    
    
    let pageUrlStr =  Config.BaseUrl + serviceName.value
    
    let headers : HTTPHeaders = [
        "Accept" : "Application/json",
        "Content-Type" : "application/x-www-form-urlencoded",
      ]
//     showLoader()
    AF.request(pageUrlStr, method : method, parameters : parameters, encoding : URLEncoding.queryString,headers : headers).responseJSON
    { response in
    switch response.result {
        
    case .success(let JSON):
        print("Response with JSON : \(JSON)")
        if let jsonDictionary = JSON as? JSONDictionary{
            withSuccess(jsonDictionary)
            print("JSON Dictionary :- ",jsonDictionary)
         }else{
            failure("Request failed with error")
        }
        
        break
    case .failure(let error):
        print("Request failed with error: \(error)")
        failure("Request failed with error: \(error)")
        
        break
    }
    }
}


//MARK: - Decodable
extension Decodable {
    init(from: Any) throws {
        let data = try JSONSerialization.data(withJSONObject: from, options: .prettyPrinted)
        let decoder = JSONDecoder()
        self = try decoder.decode(Self.self, from: data)
    }
}
