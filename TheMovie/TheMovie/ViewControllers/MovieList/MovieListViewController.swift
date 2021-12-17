//
//  MovieListViewController.swift
//  TheMovie
//
//  Created by iMac on 16/12/21.
//

import UIKit

class MovieListViewController: UIViewController {

    //MARK: Outlets
    /// Movie Table view for display movie View
    @IBOutlet weak var moviewTableView: UITableView?
    
    /// movie Datasource to setup the data on tableview
    var movieTableDataSource = MovieTableDataSource()

    override func viewDidLoad() {
        
        setUpUi()
        super.viewDidLoad()
        getMovieListData()
        // Do any additional setup after loading the view.
    }
    
   
 
}

//MARK: UI Functions Extension
extension MovieListViewController {
    /// To Setup UI data we use this function
    private func setUpUi(){
        title = "Movie List"
        // Do any additional setup after loading the view.
        moviewTableView?.dataSource = movieTableDataSource
        moviewTableView?.delegate = movieTableDataSource
        movieTableDataSource.backHandler = {[weak self] (data) in
            if let movieObject = data as? MovieResult {
                let viewController = MovieDetailsViewController.init(nibName: "MovieDetailsViewController", bundle: nil)
                viewController.movieDetails = movieObject
                self?.navigationController?.pushViewController(viewController, animated: true)

            }
        }
        registerCell()
    }
    /// Register cell file with UiCollectionView
    private func registerCell() {
        // Register CollectionViewCell Xib
        moviewTableView?.register(UINib(nibName: "MovieTableViewCell", bundle: nil), forCellReuseIdentifier: "MovieTableViewCell")
    }
}

extension MovieListViewController {
    fileprivate func getMovieListData(){
        if Connectivity.isConnectedToInternet {
            var queryParamters = JSONDictionary()
            queryParamters["api_key"] = Config.APIKey as AnyObject?
            
            API().getMovie(paramters: queryParamters) { responseObj in
                self.movieTableDataSource.movieArray = (responseObj?.results)!
                self.moviewTableView?.reloadData()
            } failure: { error in
            }
        } else {
            
        }
    }
}
