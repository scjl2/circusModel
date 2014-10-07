#!/bin/bash
#Author: Matt Luckcuck
#Date 3rd October 2014



FILE=$1
echo 'Converting...'
#Bool
sed -i 's/\\boolean/Bool/g' $1 
#True
sed -i 's/\\true/True/g' $1 
#False
sed -i 's/\\false/False/g' $1 
#Then
sed -i 's/\\then/->/g' $1 
#{
sed -i 's/\\{/{/g' $1 
#}
sed -i 's/\\}/}/g' $1 
#t1
sed -i 's/\\t1//g' $1 
#t2
sed -i 's/\\t2//g' $1 
#t3
sed -i 's/\\t3//g' $1 
#cross
sed -i 's/\\cross/./g' $1 
#\_
sed -i 's/\\_/_/g' $1 

#channelset
sed -i 's/\\channelset/nametype/g' $1 

#rchanset
sed -i 's/\\rchanset/|}/g' $1 

#lchanset
sed -i 's/\\lchanset/{|/g' $1 


#end{circusaction}
sed -i 's/\\begin{circusaction}//g' $1 

#begin{circusaction}
sed -i 's/\\end{circusaction}//g' $1 

#if
sed -i 's/\\circif/if/g' $1 
#then
sed -i 's/\\circthen/then/g' $1 
#else
sed -i 's/\\circelse/else/g' $1 
#circdef
sed -i 's/\\circdef/=/g' $1 
#~.~
sed -i 's/\~.~/./g' $1 
#~?~
sed -i 's/\~?~/?/g' $1 
#~!~
sed -i 's/\~!~/!/g' $1 
#\Skip
sed -i 's/\\Skip/SKIP/g' $1 
#\prefixcolon
sed -i 's/\\prefixcolon/:/g' $1 
#\neq
sed -i 's/\\neq/!=/g' $1 
#\circseq
sed -i 's/\\circseq/;/g' $1 
#\extchoice
sed -i 's/\\extchoice/[]/g' $1 
#\lschexpract
sed -i 's/\\lschexpract//g' $1
#\rschexpract
sed -i 's/\\rschexpract//g' $1
# \circend
sed -i 's/\\circend//g' $1
#%
sed -i 's/\%//g' $1
#\\
sed -i 's/\\\\//g' $1
#~
sed -i 's/~//g' $1
#\also
sed -i 's/\\also//g' $1
#\begin{parser}
sed -i 's/\\begin{parser}//g' $1
#\end{parser}
sed -i 's/\\end{parser}//g' $1
#\begin{schema}
sed -i 's/\\begin{schema}//g' $1
#\end{schema}
sed -i 's/\\end{schema}//g' $1



  # sed "s/$OLD/$NEW/g" "$f" > $TFILE && mv $TFILE "$f"

echo 'Done'
#The Road Goes Ever On and On
