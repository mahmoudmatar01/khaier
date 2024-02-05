# Overview
Welcome to الخيـــــــــــــــــــــــــــــــــــــــــــر - a platform that brings together donors, volunteers, and charitable organizations to make a positive impact on the world. This document provides an overview of the project, its functionality, and how to get started.

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

## UML Diagram 

```plaintext
+--------------------------------------+
|               User                   |
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
| userImage: UserImage                 |
| userImageUrl: String                 |
| posts: List<Post>                    |
| likedPosts: List<Post>               |
| comments: List<Comment>              |
| replies: List<Reply>                 |
| bookmarks: List<Post>                |
| campaigns: List<Campaign>            |
| donations: List<CampaignDonation>    |
+--------------------------------------+

+--------------------------------------+
|              Post                    |
+--------------------------------------+
| postId: Long                         |
| postContent: String                  |
| date: LocalDateTime                  |
| user: User                           |
| likes: List<Like>                    |
| comments: List<Comment>              |
| images: List<PostImage>              |
+--------------------------------------+

+--------------------------------------+
|             Comment                  |
+--------------------------------------+
| commentId: Long                      |
| content: String                      |
| date: LocalDateTime                  |
| user: User                           |
| post: Post                           |
| replies: List<Reply>                 |
+--------------------------------------+

+--------------------------------------+
|              Reply                   |
+--------------------------------------+
| replyId: Long                        |
| content: String                      |
| date: LocalDateTime                  |
| user: User                           |
| comment: Comment                     |
+--------------------------------------+

+--------------------------------------+
|              Like                    |
+--------------------------------------+
| likeId: Long                         |
| date: LocalDateTime                  |
| isLiked: boolean                     |
| user: User                           |
| post: Post                           |
+--------------------------------------+

+--------------------------------------+
|           Campaign                   |
+--------------------------------------+
| campaignId: Long                     |
| campaignName: String                 |
| campaignAdditionalName: String       |
| campaignDescription: String          |
| campaignEndDay: LocalDateTime        |
| numberOfBeneficiaries: Long          |
| amountRequired: double               |
| charitableOrganization: CharitableOrg|
| campaignImage: CampaignImage         |
| users: List<User>                    |
| donations: List<CampaignDonation>    |
+--------------------------------------+

+--------------------------------------+
|       CampaignDonation               |
+--------------------------------------+
| donationId: Long                     |
| user: User                           |
| campaign: Campaign                   |
| amount: BigDecimal                   |
| donationTime: LocalDateTime          |
+--------------------------------------+

+----------------------------------------------+
|      CharitableOrganization                  |
+----------------------------------------------+
| orgId: Long                                  |
| orgName: String                              |
| description: String                          |
| location: String                             |
| facebookUrl: String                          |
| instagramUrl: String                         |
| orgPhoneNumber: String                       |
| orgWhatsappNumber: String                    |
| charitableOrgImage: CharitableOrgImage       |
| donationCategories: List<DonationCategories> |
| donationCampaigns: List<Campaign>            |
| inKindDonations: List<InKindDonation>        |
+----------------------------------------------+

+--------------------------------------+
|     DonationCategories               |
+--------------------------------------+
| categoryId: Long                     |
| categoryTitle: String                |
| charitableOrganization: CharitableOrg|
| donationCases: List<DonationCase>    |
+--------------------------------------+

+--------------------------------------+
|         DonationCase                 |
+--------------------------------------+
| caseId: Long                         |
| caseName: String                     |
| description: String                  |
| donationCategory: DonationCategories |
+--------------------------------------+

+--------------------------------------+
|       InKindDonation                 |
+--------------------------------------+
| id: Long                             |
| title: String                        |
| description: String                  |
| charitableOrganization: CharitableOrg|
+--------------------------------------+

```

##  Use Case Diagram
The following diagram is a depiction of the UML diagram of our project.
<img src="https://rebekia-api-02084fade382.herokuapp.com/api/v1/auth/images/photo-1c87b029-4bea-413a-9258-cde5616c02a9" />

Each use case in a use case diagram can have an associated behavior specification that describes the sequence of actions that make up the use case scenario.
