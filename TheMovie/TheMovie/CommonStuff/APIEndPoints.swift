//
//  APIEndPoints.swift
//  TheMovie
//
//  Created by iMac on 16/12/21.
//

import Foundation
 
 
 
enum APIEndPoint {
  case movie
    case moviedetails(String)

  var value: String {
    switch self {
    case .movie:
      return "/discover/movie"
    case .moviedetails(let customValue):
      return "/movie/\(customValue)"
    }
  }
}
