//
//  MovieTableViewCell.swift
//  TheMovie
//
//  Created by iMac on 16/12/21.
//

import UIKit

class MovieTableViewCell: UITableViewCell {

    //MARK: Outlets
    @IBOutlet weak var titleLabel: UILabel?
    @IBOutlet weak var dateLabel: UILabel?
    @IBOutlet weak var descriptionLabel: UILabel?
    @IBOutlet weak var avarageVoteLabel: UILabel?
    @IBOutlet weak var movieImageView: UIImageView?

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
