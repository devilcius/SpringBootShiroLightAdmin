
//Gruntfile
module.exports = function (grunt) {

//Initializing the configuration object
    grunt.initConfig({
// Task configuration
        concat: {
            options: {
                separator: ';'
            },
            js_vendor: {
                src: [
                    './bower_components/bootstrap/dist/js/bootstrap.js',
                ],
                dest: './src/main/resources/static/assets/js/vendor.js'
            },
            css_vendor: {
                src: [
                    './bower_components/font-awesome/css/font-awesome.css',
                    './bower_components/bootstrap/dist/css/bootstrap.css'
                ],
                dest: './src/main/resources/static/assets/css/vendor.css'
            }
        },
        uglify: {
            options: {
                mangle: false  // Use if you want the names of your functions and variables unchanged
            },
            vendor: {
                files: {
                    './src/main/resources/static/assets/js/vendor.js': './src/main/resources/static/assets/js/vendor.js'
                }
            }
        },
        cssmin: {
            options: {
                // see https://github.com/jakubpawlowicz/clean-css#how-to-use-clean-css-programmatically
            },
            vendor: {
                files: {
                    './src/main/resources/static/assets/css/vendor.css': './src/main/resources/static/assets/css/vendor.css'
                }
            }
        },
        copy: {
            dist: {
                files: [
                    {//for bootstrap fonts
                        expand: true,
                        dot: true,
                        cwd: 'bower_components/bootstrap/dist',
                        src: ['fonts/*.*'],
                        dest: './src/main/resources/static/assets/'
                    },
                    {//for bootstrap maps
                        expand: true,
                        dot: true,
                        cwd: 'bower_components/bootstrap/dist',
                        src: ['css/*.map'],
                        dest: './src/main/resources/static/assets/'
                    },
                    {//for font-awesome
                        expand: true,
                        dot: true,
                        cwd: 'bower_components/font-awesome',
                        src: ['fonts/*.*'],
                        dest: './src/main/resources/static/assets/'
                    }
                ]
            }
        }
    });
    // // Plugin loading
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-copy');

    // Task definition
    grunt.registerTask('default', ['concat', 'copy', 'cssmin', 'uglify']);

};
