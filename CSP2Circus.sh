#!/bin/bash
#Author: Matt Luckcuck
#Script to help with converting CSP to Circus. It helps, but by no means automates the whole the process!

#Original Date 11th February 2015

#  s/regexp/replacement/


#############BROKEN

#FILE=$1
echo 'NOT Converting...'


################
###SUBSTITUTE###
################

#Bool
#sed -i 's/Bool/\\boolean/g' $1 
#True
sed -i 's/True/\\true/g' $1 
#False
sed -i 's/False/\\false/g' $1 
#Then
sed -i 's/->/\\then/g' $1 


#cross
#sed -i 's/\\cross/./g' $1 
#\_
sed -i 's/_/\\_/g' $1 

#\circchannel
sed -i 's/channel/\\circchannel/g' $1 
#channelset
#sed -i 's/\\channelset/nametype/g' $1 

#rchanset
sed -i 's/|}/\\rchanset/g' $1 

#lchanset
sed -i 's/{|/\\lchanset/g' $1 

#if
sed -i 's/if/\\circif/g' $1 
#then
sed -i 's/then/\\circthen/g' $1 
#else
sed -i 's/else/\\circelse/g' $1 
#fi
#sed -i 's/\\circfi//g' $1
#circdef
sed -i 's/=/\\circdef/g' $1 
#~?~
sed -i 's/?/~?~/g' $1 
#~!~
sed -i 's/!/~!~/g' $1 
#~.~
sed -i 's/./~.~/g' $1 
#\Skip
sed -i 's/SKIP/\\Skip/g' $1 
#\prefixcolon
#sed -i 's/\\prefixcolon/:/g' $1 
#\neq
sed -i 's/!=/\\neq/g' $1 
#\circseq
sed -i 's/;/\\circseq/g' $1 
#\extchoice
sed -i 's/\[\]/\\extchoice/g' $1 


#\interleave
sed -i 's/|||/\\interleave/g' $1
#\Interleave
#sed -i 's/\\Interleave/|||/g' $1

#\Chaos
sed -i 's/Chaos(Events)/\\Chaos/g' $1






  # sed "s/$OLD/$NEW/g" "$f" > $TFILE && mv $TFILE "$f"

echo 'Done'
#The Road Goes Ever On and On
