//
//  MovieDetailsViewController.swift
//  TheMovie
//
//  Created by iMac on 17/12/21.
//

import UIKit

class MovieDetailsViewController: UIViewController {
    
    
    /// Movie details object to get movie data
    var movieDetails : MovieResult?
   
    //MARK: Outlets
    @IBOutlet weak var titleLabel: UILabel?
    @IBOutlet weak var dateLabel: UILabel?
    @IBOutlet weak var descriptionLabel: UILabel?
    @IBOutlet weak var avarageVoteLabel: UILabel?
    @IBOutlet weak var movieImageView: UIImageView?
    @IBOutlet weak var posterImageView: UIImageView?
    @IBOutlet weak var genresLabel: UILabel?
    @IBOutlet weak var productionLabel: UILabel?
    @IBOutlet weak var languagesLabel: UILabel?

    override func viewDidLoad() {
        setUpUi()
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
 
}
//MARK: UI Functions Extension
extension MovieDetailsViewController {
    /// To Setup UI data we use this function
    private func setUpUi(){
        title = movieDetails!.title
        self.titleLabel?.text = movieDetails!.originalTitle
        self.dateLabel?.text = movieDetails!.releaseDate
        self.descriptionLabel?.text = movieDetails!.overview
        self.avarageVoteLabel?.text = String(movieDetails!.voteAverage!)
        let imagepath =  "https://image.tmdb.org/t/p/w500" + movieDetails!.backdropPath!
        let backImagepath =  "https://image.tmdb.org/t/p/original" + movieDetails!.posterPath!

        self.movieImageView?.sd_setImage(with: URL(string: backImagepath ), placeholderImage: UIImage(named: "coderkube_logo")!)
        self.posterImageView?.sd_setImage(with: URL(string: imagepath ), placeholderImage: UIImage(named: "coderkube_logo")!)
        // Do any additional setup after loading the view.
        getMovieData()
     }
   
    private func setData(movieDetails:MovieDetails){
        self.genresLabel?.text = "Genres :" +  (movieDetails.genres!.map { ($0.name!) }).joined(separator: ", ")

    
        self.productionLabel?.text = "Production Companies :" +  (movieDetails.productionCompanies!.map { ($0.name!) }).joined(separator: ", ")

        self.languagesLabel?.text = "Languages :" +  (movieDetails.spokenLanguages!.map { ($0.name!) }).joined(separator: ", ")
    }
}

//MARK: Api calling 
extension MovieDetailsViewController {
    fileprivate func getMovieData(){
        if Connectivity.isConnectedToInternet {
            var queryParamters = JSONDictionary()
            queryParamters["api_key"] = Config.APIKey as AnyObject?
            
            API().getMovieDetails(movieid:String(movieDetails!.id!), paramters: queryParamters) { [weak self] responseObj in
                self?.setData(movieDetails: responseObj!)
                
            } failure: { error in
            }
        } else {
            
        }
    }
}
