# Changelog
The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [0.14.0] - UNRELEASED - PROJECT ABANDONNED
- Game: Adjust in cards (split in levels / blocking high level cards)
- UI: Improvements in card selector, selected card and report
- UI: Adding Money and Weapon features

## [0.13.0] - 2018-10-15
### Changed
- UI: Preloading images
- UI: Improvements in card selector / Zoom


## [0.12.0] - 2018-07-29
### Changed
- Performance: Removing font from external CDN 
- UI: Fixing report table
- UI: Replacing cards by quads
- UX: Adding keyboard shortcuts
- UX: Adding icons
- UI: Adding fog and some animations

## [0.11.0] - 2018-07-11
### Changed
- UI: Applying RPG style
- Internal: Removed workaround for GoDaddy /foo/bar url problem
- Internal: Adding Spring Dev Tools only locally
- Internal: Compiling Bootstrap locally

## [0.10.0] - 2018-07-08
### Changed
- Internal: Adjusts in DevOps

## [0.9.0] - 2018-07-08
### Changed
- Internal: Adding Jenkins and Nginx to EC2

## [0.8.1] - 2018-07-03
### Fixed
- UI: Fixing target of Docker Hub link
- Core: eternal loop in Drools due to springboot devtools
### Changed
- Internal: adjusts in automation

## [0.8.0] - 2018-07-02
### Changed
- Internal: replacing AWS Elastic Beanstalk by manual deploy
- Internal: replacing Travis-CI by Jenkins (partial)
- Internal: updating base docker image (from JRE 8u151 to 8u171)

## [0.7.0] - 2018-06-25
### Changed
- UI: minor adjusts
- Internal: fixed deploy with Travis, using npm to install sass
- Internal: Spring updated from 2.0.1 to 2.0.3 + Added Spring DevTools

## [0.6.0] - 2018-06-24
### Added
- UI: adding CSS Bootstrap and SASS
- Internal: allowing deploy without the unstable DockerHub

### Changed
- UI: creating welcome screen
- UI: removing not ready cards
- UI: removing footer
- Usage of "Keep a Changelog"
- Huge Refactor in tasks files

## [0.5.1] - 2018-06-17
### Fixed
- UI: Fixing images not working in paths like /foo/bar

## [0.5.0] - 2018-06-16
### Added
- Core: read cards from CSV file
- UI: Adding icon to cards
- UI: Adding ALPHA ribbon and footer
- HTML: Improvements in HTML to be more semantic: header, footer tags for example
### Changed
- UI: Removing some elements to make UI more simple

## [0.4.0] - 2018-06-16
### Changed
- Nothing. Created by mistake

## [0.3.0] - 2018-06-14
### Changed
- Deploy in AWS using eb cli tool
- Deploy in AWS using DockerHub image
- Adjust to use port 5000 in all cases

## [0.2.0] - 2018-06-13
### Changed
- Replacement of JDK by JRE and upgrade to Java 8u151
- Creating draft of first cards
- Add versioning system on Docker Hub

## [0.1.0] - 2018-06-10
### Added
- Create diff between version and build number
- Use spring cache to cache result of games
- Add life bar
- Reduce verbosity of aws deploy command...
- Deploy using aws instead of eb
- Restore sonar analysis
- Make travis deploy to AWS
- Restore AWS EB deploy
- Improve speed of dev branch in travis, skipping instalation of awseb
- Master branch is failing because there are no elastic beanstalk configured...
- Fix travis: publish image is running before tests
- Travis: run tests in parallel and use cache
- Deploy using docker
- Improve creation of docker image in docker hub: use jar generated in travis-ci
- Make travis-ci generate jar of springboot and make public
- Implement CI/CD environment with Travis and DockerHub
- Use docker to package the application
- Use spring cache annotation