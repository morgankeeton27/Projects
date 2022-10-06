/*Produces tables with the top number of specified values 
(chosen by the user) for the country (user specified. Then does
an exploratory analysis of the video data.*/
ods html style=htmlbluecml;
/*user specifies country from the list of 10 countries
user specified variables from the list of variables
user then specifies the number of desired observations*/
%macro videos(country = , var = , num = );
data &country;
	%let _EFIERR_ = 0; /* set the ERROR detection macro variable */
     infile "M:\STA402\Project\Data\&country.videos.csv"
		delimiter = ',' 
		MISSOVER 
		DSD 
		lrecl=32767
		firstobs=2 ;

	*used PROC IMPORT to get the format;
	format video_id $11. trending_date $8. title $88. 
			channel_title $23. category_id best12. 
			publish_time B8601DZ35. tags $538.
			views best12. likes best12. dislikes best12.
			comment_count best12. thumbnail_link $46.
			comments_disabled $5. ratings_disabled $5.
			video_error_or_removed $5. description $2259.;

	input video_id $ trending_date $ title $ channel_title $
				category_id publish_time tags $ views likes 
				dislikes comment_count thumbnail_link $
				comments_disabled $ ratings_disabled $
				video_error_or_removed $ description $;
	 if _ERROR_ then call symputx('_EFIERR_',1);
run;

data work.&country;
	set &country;
	keep tags views likes dislikes comment_count;
run;

ods rtf bodytitle file = "M:\STA402\Project\&country..rtf";

*prints figures 2 through 4 for respective countries;
title "5 Number Summary of &country Data";
proc means data = work.&country min mean median max;
	var likes dislikes views comment_count;
	label likes = "Number of Likes"
			dislikes = "Number of Dislikes"
			views = "Number of Views"
			comment_count = "Number of Comments";
run;

proc sort data = &country out = sorted;
	by descending &var;
run; 

*only keeps 10 observations;
data sorted2;
	set sorted (obs = &num);
	keep tags &var;
	label tags = "Tags";
	label  &var = "Number of &var";
run;

*prints the data;
*prints figures 5 through 7 for respective countries;
title "Top 10 Video Tags with highesy &var";
title2 "from &country Video Data";
proc print data = sorted2 label;
run;

ods rtf close;
%mend videos;
*call macro below;
/*%videos(country = US, var = dislikes , num = 3)
%videos(country = RU, var = comment_count, num = 3)
%videos(country = MX, var = views, num = 3)

*/

/*This begins our overall exploratory analysis*/

*the following macro reads in the data for each country;
*it then sorts the data by likes;
%macro exploratory(data = ); *data = user specified country;
data &data;
	%let _EFIERR_ = 0; /* set the ERROR detection macro variable */
     infile "M:\STA402\Project\Data\&data.videos.csv"
		delimiter = ',' 
		MISSOVER 
		DSD 
		lrecl=32767
		firstobs=2 ;

	*used PROC IMPORT to get the format;
	format video_id $11. trending_date $8. title $88. 
			channel_title $23. category_id best12. 
			publish_time B8601DZ35. tags $538.
			views best12. likes best12. dislikes best12.
			comment_count best12. thumbnail_link $46.
			comments_disabled $5. ratings_disabled $5.
			video_error_or_removed $5. description $2259.;

	input video_id $ trending_date $ title $ channel_title $
				category_id publish_time tags $ views likes 
				dislikes comment_count thumbnail_link $
				comments_disabled $ ratings_disabled $
				video_error_or_removed $ description $;
	 if _ERROR_ then call symputx('_EFIERR_',1);
run;
%mend exploratory;
*calls each country;
%exploratory(data = US)
%exploratory(data = RU)
%exploratory(data = CA)
%exploratory(data = KR)
%exploratory(data = JP)
%exploratory(data = DE)
%exploratory(data = GB)
%exploratory(data = MX)
%exploratory(data = IN)
%exploratory(data = FR)

%macro sortem(country2 = );
proc sort data = &country2 out = &country2_sorted;
	by likes;
run;
%mend sortem;
%sortem(country2 = US)
%sortem(country2 = RU)
%sortem(country2 = CA)
%sortem(country2 = KR)
%sortem(country2 = JP)
%sortem(country2 = DE)
%sortem(country2 = GB)
%sortem(country2 = MX)
%sortem(country2 = IN)
%sortem(country2 = FR)

data all;
	merge  US_sorted RU_sorted MX_sorted KR_sorted 
			JP_sorted IN_sorted GB_sorted FR_sorted 
			DE_sorted CA_sorted;
	by likes;
	keep tags views likes comment_count dislikes;
run;
ods rtf bodytitle file = "M:\STA402\Project\graphs2.rtf";
title "Relationship of Views and Likes";
title2 "from all video country data";

proc sgplot data = all;
	series x = views y = likes / markers;
	label views = "Number of Views"
			likes = "Number of Likes";
run;
title "Relationship of Views and Comment Count";
title2 "from all video country data";
proc sgplot data = all;
	series x = views y = comment_count / markers;
	label views = "Number of Views"
			comment_count = "Number of Comments";
run;
title "Relationship of Views and Dislikes";
title2 "From all video country data";
proc sgplot data = all;
	series x = views y = dislikes / markers;
	label views = "Number of Views"
			comment_count = "Number of Comments";
run;
ods rtf close;

ods rtf bodytitle file = "M:\STA402\Project\numbersanduniv.rtf";
*let's look at a number summary;
title "Numerical Analysis of Country Video Data";
proc means data = all min median mean max;
	vars likes dislikes views comment_count;
	label likes = "Number of Likes"
			dislikes = "Number of Dislikes"
			views = "Number of Views"
			comment_count = "Number of Comments";
run;
*let's explore the distribution of our data;
title "Univariate Exploration of Country Data";
*Figure Eight and Nine;
proc univariate data = all;
	var likes dislikes views comment_count;
	histogram likes / normal;
	qqplot likes / normal(mu=est sigma=est);
	histogram views / normal;
	qqplot views / normal(mu = est sigma = est);
	histogram dislikes / normal;
	qqplot dislikes / normal (mu = est sigma = est);
	histogram comment_count / normal;
	qqplot comment_count / normal (mu = est sigma = est);
	label likes = "Number of Likes"
			views = "Number of Views"
			dislikes = "Number of Dislikes"
			comment_count = "Number of Comments";
run;
ods rtf close;
