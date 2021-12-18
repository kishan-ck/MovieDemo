//
//  MovieTableDataSource.swift
//  TheMovie
//
//  Created by iMac on 16/12/21.
//

import Foundation
import UIKit
import SDWebImage
class MovieTableDataSource: NSObject, UITableViewDelegate, UITableViewDataSource {
    
    //define variables
    var movieArray =  [MovieResult]()
    
    var backHandler : backDataHandler?

    func numberOfSections(in tableView: UITableView) -> Int
    {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return movieArray.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let tableViewCell = tableView.dequeueReusableCell(withIdentifier: "MovieTableViewCell", for: indexPath) as! MovieTableViewCell
        let movieData = movieArray[indexPath.row]
        tableViewCell.titleLabel?.text = movieData.originalTitle
        tableViewCell.dateLabel?.text = movieData.releaseDate
        tableViewCell.descriptionLabel?.text = movieData.overview
        tableViewCell.avarageVoteLabel?.text = String(movieData.voteAverage!)
        let imagepath =  "https://image.tmdb.org/t/p/w500" + movieData.backdropPath!
        
        tableViewCell.movieImageView?.sd_setImage(with: URL(string: imagepath ), placeholderImage: UIImage(named: "coderkube_logo")!)
        tableViewCell.selectionStyle = .none
        return tableViewCell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let movieData = movieArray[indexPath.row]
        guard let completionBlock = self.backHandler  else {return}
        completionBlock(movieData)
     }
}
