# Train a Model Using Vowpal Wabbit #

Should you wish to train model coefficients using Vowpal Wabbit please
follow the instructions below.

You will need to have a Vowpal Wabbit source installation containing
the script *vw-varinfo*.  Set the environment as appropriate on your machine, e.g.,

    export VOWPAL_HOME=$HOME/vowpal_wabbit

verify the installation has the necessary script

    ls $VOWPAL_HOME/utl/vw-varinfo

run the training script

    cd $ANTELOPE_TRAINING
    $ANTELOPE_DEMO/antelope/scripts/vw/train.sh

now inspect the model parameters

    cat vw_logit_coef.txt

you should see the following

    +1.0174,+0.0429,+0.1487,+0.0554,-0.0397

The values hardcoded in the class
[co.ifwe.antelope.bestbuy.exec.LearnedRankerScoring](../demo/src/main/scala/co/ifwe/antelope/bestbuy/exec/LearnedRankerScoring.scala)
were fit using R.  There is no need to change them now but you can replace them with the Vowpal Wabbit coefficients and
expect to get very similar results.

Now head on back and follow the rest of the [demo](demo.md) to test predictions made using the model.