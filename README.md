# PiApproximator
Used to approximate n digits of pi, uses threading for maximum <i>speed</i><br>
Uses the <a href=https://en.wikipedia.org/wiki/Ramanujan%E2%80%93Sato_series#Level_1>Ramunjan-Sato series</a> to approximate<br>
ThreadManager is used to control everything. lower should typically be 0, upper should be the number of times you want to do the summation function, mc is the digits of pi that you want to calculate, incr is the number of itterations per thread, numThreads is the number of threads.<br>
the number of times that you do the summation function (upper - lower) is how accurate the approximation. you get about 7 * upper accurate digits.<br>
incr should alway be a factor of upper - lower.<br>
