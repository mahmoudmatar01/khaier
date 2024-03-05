# Platform - الخيــــــــــــــــــــــــــــــــــــــــــــــــر

# Table of Contents
1. [Overview](#overview)
2. [Background](#background)
3. [Features](#features)
4. [Database Schema](#database-schema)
5. [UML Diagram](#uml-diagram)
6. [Requirements Specification](#requirements-specification)
7. [Use Case Diagram](#use-case-diagram)
8. [ERD Diagram](#erd-diagram)

## Overview
Welcome to الخيـــــــــــــــــــــــــــــــــــــــــــر - a platform that brings together donors, volunteers, and charitable organizations to make a positive impact on the world. This document provides an overview of the project, its functionality, and how to get started.

## Background:
In today's interconnected world, being aware of social issues and supporting charitable efforts is important for helping people in need. But it can be hard to find the right charities and know how to contribute. This project aims to solve that problem by connecting charitable organizations with people who want to help. It makes it easier for everyone to work together to make a positive difference.

## Features
### User Registration and Login
- Donors, volunteers, and charitable organizations can create accounts.
- Authentication and authorization ensure role-specific access.

### For Donors
1. View charity cases in each organization.
2. Search for specific organizations.
3. Select a donation type (money, clothes, food) for a charity case or campaign.
4. Filter charity cases based on categories.
5. Post a charity case for review by administrators.
6. Interact with posts (like, comment, save).
7. Apply for volunteering.
8. Report issues faced during donations.
9. Track donation history.

### For Charitable Organizations
1. Post upcoming campaigns and charity cases.
2. Manage urgent charity cases and set urgency levels.
3. Receive notifications when a case is fully donated.

## Database Schema
The application uses a relational database with tables for users, posts, comments, likes, bookmarks, charitable organizations, campaigns, campaign donations, donation categories, donation cases, in-kind donations, and gift donations. Refer to the Database Schema for a detailed structure.

```plaintext
Table: users
- userId (PK)
- username
- userImageUrl
- email
- password
- userRole
- userGender
- location
- phone
- accessToken

Table: posts
- postId (PK)
- postContent
- date
- userId (FK references users.userId)

Table: comments
- commentId (PK)
- content
- date
- userId (FK references users.userId)
- postId (FK references posts.postId)

Table: likes
- likeId (PK)
- date
- isLiked
- userId (FK references users.userId)
- postId (FK references posts.postId)

Table: bookmarks
- bookmarkId (PK)
- date
- userId (FK references users.userId)
- postId (FK references posts.postId)

Table: charitable_organizations
- orgId (PK)
- orgName
- description
- location
- facebookUrl
- instagramUrl
- orgPhoneNumber
- orgWhatsappNumber
- charitableOrgImageId (FK references charitable_org_images.charitableOrgImageId)

Table: campaigns
- campaignId (PK)
- campaignName
- campaignAdditionalName
- campaignDescription
- campaignEndDay
- numberOfBeneficiaries
- amountRequired
- orgId (FK references charitable_organizations.orgId)
- campaignImageId (FK references campaign_images.campaignImageId)

Table: campaign_donations
- donationId (PK)
- userId (FK references users.userId)
- campaignId (FK references campaigns.campaignId)
- amount
- donationTime

Table: donation_categories
- categoryId (PK)
- categoryTitle
- orgId (FK references charitable_organizations.orgId)

Table: donation_cases
- caseId (PK)
- caseName
- description
- categoryId (FK references donation_categories.categoryId)

Table: in_kind_donations
- id (PK)
- title
- description
- orgId (FK references charitable_organizations.orgId)

Table: gift_donations
- giftDonationId (PK)
- userId (FK references users.userId)
- amount
- donationMessage
```

## UML Diagram 
The UML diagram illustrates the relationships between various entities in the system. Refer to the UML Diagram section for a visual representation.
```plaintext
+--------------------------------------+
|             User                     |
+--------------------------------------+
| userId: Long                         |
| username: String                     |
| email: String                        |
| password: String                     |
| userRole: Role                       |
| userGender: Gender                   |
| location: String                     |
| phone: String                        |
| accessToken: String                  |
| posts: List<Post>                    |
| likedPosts: List<Post>               |
| comments: List<Comment>              |
| replies: List<Reply>                 |
| bookmarks: List<Post>                |
| campaigns: List<Campaign>            |
| donations: List<CampaignDonation>    |
| inKindDonations: List<InKindDonation>|
| caseDonations: List<CaseDonation>    |
| giftDonations: List<GiftDonation>    |
+--------------------------------------+

+----------------------------------+
|             Post                 |
+----------------------------------+
| postId: Long                     |
| postContent: String              |
| date: LocalDateTime              |
| user: User                       |
| likes: List<Like>                |
| comments: List<Comment>          |
| images: List<PostImage>          |
+----------------------------------+

+----------------------------------+
|             Like                 |
+----------------------------------+
| likeId: Long                     |
| date: LocalDateTime              |
| isLiked: boolean                 |
| user: User                       |
| post: Post                       |
+----------------------------------+

+----------------------------------+
|           Comment                |
+----------------------------------+
| commentId: Long                  |
| content: String                  |
| date: LocalDateTime              |
| user: User                       |
| post: Post                       |
| replies: List<Reply>             |
+----------------------------------+

+----------------------------------+
|            Reply                 |
+----------------------------------+
| replyId: Long                    |
| content: String                  |
| date: LocalDateTime              |
| user: User                       |
| comment: Comment                 |
+----------------------------------+

+---------------------------------------------+
| CharitableOrganization                      |
+---------------------------------------------+
| orgId: Long                                 |
| orgName: String                             |
| description: String                         |
| location: String                            |
| facebookUrl: String                         |
| instagramUrl: String                        |
| orgPhoneNumber: String                      |
| orgWhatsappNumber: String                   |
| charitableOrgImage: CharitableOrgImage      |
| donationCategories: List<DonationCategories>|
| donationCampaigns: List<Campaign>           |
| inKindDonations: List<InKindDonation>       |
| caseDonations: List<CaseDonation>           |
+---------------------------------------------+

+-----------------------------------------------+
|      DonationCategories                       |
+-----------------------------------------------+
| categoryId: Long                              |
| categoryTitle: String                         |
| charitableOrganization: CharitableOrganization|
| donationCases: List<DonationCategoryCase>     |
+-----------------------------------------------+

+-------------------------------------+
|    DonationCategoryCase             |
+-------------------------------------+
| caseId: Long                        |
| caseName: String                    |
| maxim: String                       |
| description: String                 |
| requiredAmount: BigDecimal          |
| paidAmount: BigDecimal              |
| remainingAmount: BigDecimal         |
| donationCategory: DonationCategories|
| donations: List<CaseDonation>       |
+-------------------------------------+

+-------------------------------------+
|         CaseDonation                |
+-------------------------------------+
| donationId: Long                    |
| user: User                          |
| organization: CharitableOrganization|
| donationCase: DonationCategoryCase  |
| donationTime: LocalDateTime         |
| donationWay: String                 |
| amount: BigDecimal                  |
+-------------------------------------+

+-----------------------------------------------+
|            Campaign                           |
+-----------------------------------------------+
| campaignId: Long                              |
| campaignName: String                          |
| campaignAdditionalName: String                |
| campaignDescription: String                   |
| campaignEndDay: LocalDateTime                 |
| numberOfBeneficiaries: Long                   |
| amountRequired: double                        |
| charitableOrganization: CharitableOrganization|
| campaignImage: CampaignImage                  |
| users: List<User>                             |
| donations: List<CampaignDonation>             |
+-----------------------------------------------+

+----------------------------------+
|      CampaignDonation            |
+----------------------------------+
| donationId: Long                 |
| user: User                       |
| campaign: Campaign               |
| amount: BigDecimal               |
| donationTime: LocalDateTime      |
+----------------------------------+

+----------------------------------+
|          InKindCase              |
+----------------------------------+
| id: Long                         |
| title: String                    |
| includedItemName: String         |
| donations: List<InKindDonation>  |
+----------------------------------+

+-------------------------------------+
|      InKindDonation                 |
+-------------------------------------+
| donationId: Long                    |
| user: User                          |
| phone: String                       |
| organization: CharitableOrganization|
| inKindCase: InKindCase              |
| itemAmount: BigDecimal              |
| itemName: String                    |
| donationTime: LocalDateTime         |
| lang: Float                         |
| lat: Float                          |
| addressDescription: String          |
+-------------------------------------+

+-----------------------------------+
|         GiftDonation              |
+-----------------------------------+
| id: Long                          |
| giftDonationType: GiftDonationType|
| amount: BigDecimal                |
| sender: User                      |
| receiverName: String              |
| receiverPhone: String             |
| message: String                   |
+-----------------------------------+

+----------------------------------+
|           Banner                 |
+----------------------------------+
| bannerId: Long                   |
| title: String                    |
| description: String              |
| imageUrl: String                 |
| bannerImage: BannerImage         |
+----------------------------------+

+----------------------------------+
|         Bookmark                 |
+----------------------------------+
| bookmarkId: Long                 |
| date: LocalDateTime              |
| user: User                       |
| post: Post                       |
+----------------------------------+

```

## Requirements Specification
Software Requirement specification “SRS” is a process of writing down all the system and user requirements. And in the following subsection, we’ll explain more about the functional requirements of the application.

### Functional requirements
It’s the answer to the question of what the system does as it highlights the functions/features which the application offers. 
The first and most important function for all users is:

- User registration and Login: Donors, volunteers, and charity organizations can create a new account and log in to access their corresponding user interface. This applies the concept of authentication and authorization as each user has a specific role that enables him/her to access specific content.
  
- For the donors:
1.	View the charity cases in each organization.
2.	Search for a specific organization.
3.	Select the donation type to a charity case or a campaign like donating money, clothes, or food. 
4.	Filter the charity cases based on their category.
5.	Post a charity case to the application which will be pending before being reviewed by the administrators.
6.	Interact with another person’s post by liking, commenting, or saving that post.
7.	Donors can also apply for volunteering.
8.	Issue a problem that he/she faced when donating to a specific organization.
9.	Track your donations.
    
- For charity organizations:
1.	Post their upcoming campaigns like Food Bank or donating to Gaza, these campaigns are bigger and take a longer time to complete than a charity case.
2.	They can post charity cases like families that need help or a person that needs urgent surgery, these cases are shown on the application according to their urgency and the charity needs to select how urgent the case is.
3.	Get notified when the case has been fully donated so that they can proceed with their work.
   
###	Non-Functional Requirements
It’s a set of specifications that describe the system’s operation capabilities and constraints.
1. `Responsiveness`: The app responds quickly to user interactions and page loads, regardless of device or network conditions with a maximum load time of 5 seconds.
2. `Data Security`: The user’s login information is encrypted using JWT authentication and Password encoders to make sure that intruders cannot access users’ private data like their payment data.
3. `Availability`: As the Railway server is almost 24/7 available, there might be outages or downtime but it is insignificant and most of the time it would be some maintenance and upgrades in the server which happen in the less busy hours and are pre-announced.

##  Use Case Diagram
The Use Case Diagram provides a visual representation of the interactions between users and the system. Refer to the Use Case Diagram section for a detailed illustration.
<img src="https://raw.githubusercontent.com/mahmoudmatar01/khaier/master/assets/usecases_diagram.png" />
Each use case in a use case diagram can have an associated behavior specification that describes the sequence of actions that make up the use case scenario.


##  ERD Diagram
The Entity-Relationship Diagram (ERD) illustrates the relationships between entities in the database. Refer to the ERD Diagram section for a visual representation.
<img src="https://raw.githubusercontent.com/mahmoudmatar01/khaier/master/assets/ERD_diagram.png" />
