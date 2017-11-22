<%-- 
    Document   : recovery
    Created on : Nov 14, 2017, 1:36:41 AM
    Author     : Felipe
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">

<script type="text/javascript"
	src="js/lib/mootools-core-1.4.5-nocompat.js"></script>
<style type="text/css">
#password {
	padding: 10px;
	margin: 0 0 25px;
}

div.pass-container {
	height: 30px;
}

div.pass-bar {
	height: 11px;
	margin-top: 2px;
}

div.pass-hint {
	color: white;
	font-size: 17px;
	margin-top: 5px;
}
</style>

<title>Nova senha</title>

<script type='text/javascript'>//<![CDATA[
            window.addEvent('load', function () {
                /*
---

name: StrongPass.js

description: checks password strength of a string

authors: Dimitar Christoff

license: MIT-style license.

version: 1.0.3

requires:
- Core/String
- Core/Element
- Core/DOMEvent
- Core/Class
- Core/Array

provides: StrongPass

...
*/
                (function () {
                    'use strict';

                    var StrongPass = new Class({

                        Implements: [Options, Events],

                        options: {

                            minChar: 6, // too short while less than this

                            passIndex: 2, // Weak

                            // output verdicts, colours and bar %
                            label: 'Força da senha: ',

                            verdicts: [
                                'Muito curta',
                                'Muito fraca',
                                'Fraca',
                                'Boa',
                                'Forte',
                                'Muito forte'
                            ],

                            colors: [
                                '#ccc',
                                '#500',
                                '#800',
                                '#f60',
                                '#050',
                                '#0f0'
                            ],

                            width: [
                                '0%',
                                '10%',
                                '30%',
                                '60%',
                                '80%',
                                '100%'
                            ],

                            // tweak scores here
                            scores: [
                                10,
                                15,
                                25,
                                45
                            ],

                            // when in banned list, verdict is:
                            bannedPass: 'Não permitida',

                            // styles
                            passStrengthZen: 'div.pass-container',

                            passbarClassZen: 'div.pass-bar', // css controls

                            passbarHintZen: 'div.pass-hint',

                            // output
                            render: true, // it can just report for your own implementation

                            injectTarget: null,

                            injectPlacement: 'after'
                        },

                        bannedPasswords: [
                            // see study here: http://smrt.io/JlNfrH
                            '123456',
                            '12345',
                            '123456789',
                            '1234567890',
                            '12345678901',
                            '123456789012',
                            '1234567890123',
                            '12345678901234',
                            '123456789012345',
                            '1234567890123456',
                            '12345678901234567',
                            '123456789012345678',
                            '1234567890123456789',
                            '12345678901234567890',
                            'password',
                            'iloveyou',
                            'princess',
                            'rockyou',
                            '1234567',
                            '12345678',
                            'abc123',
                            'nicole',
                            'daniel',
                            'babygirl',
                            'monkey',
                            'jessica',
                            'lovely',
                            'michael',
                            'ashley',
                            '654321',
                            'qwerty',
                            'password1',
                            'welcome',
                            'welcome1',
                            'password2',
                            'password01',
                            'password3',
                            'p@ssw0rd',
                            'passw0rd',
                            'password4',
                            'password123',
                            'summer09',
                            'password6',
                            'password7',
                            'password9',
                            'password8',
                            'welcome2',
                            'welcome01',
                            'winter12',
                            'spring2012',
                            'summer12',
                            'summer2012',
                            'senha123',
                            'senha0123456',
                            '123senha',
                        ],

                        /**
                         * @constructor
                         * @param {DOMElement} element Base element to attach to
                         * @param {Object} options* Options to merge in / attach events from
                         * @fires StrongPass#ready
                         * @returns SrongPass
                         */
                        initialize: function (element, options) {
                            this.setOptions(options);
                            this.element = document.id(element);
                            this.options.render && this.createBox();
                            this.attachEvents();
                            this.fireEvent('ready');
                        },

                        /**
                         * @description Attaches events and saves a reference
                         * @returns {StrongPass}
                         */
                        attachEvents: function () {
                            // only attach events once so freshen
                            this.eventObj && this.element.removeEvents(this.eventObj);
                            this.element.addEvents(this.eventObj = {
                                keyup: this.runPassword.bind(this)
                            });

                            return this;
                        },

                        /**
                         * @description Attaches pass elements.
                         * @returns {StrongPass}
                         */
                        createBox: function () {
                            //todo: should be templated
                            var width = this.element.getSize().x,
                                    o = this.options;

                            this.stbox = new Element(o.passStrengthZen, {
                                styles: {
                                    width: width
                                }
                            });

                            this.stdbar = new Element(o.passbarClassZen, {
                                styles: {
                                    width: width - 2
                                }
                            }).inject(this.stbox);

                            this.txtbox = new Element(o.passbarHintZen).inject(this.stbox);
                            this.stbox.inject((document.id(o.injectTarget) || this.element), o.injectPlacement);

                            return this;
                        },

                        /**
                         * @description Runs a password check on the keyup event
                         * @param {Object} event*
                         * @param {String} password* Optionally pass a string or go to element getter
                         * @fires StrongPass#fail StrongPass#pass
                         * @returns {StrongPass}
                         */
                        runPassword: function (event, password) {
                            password = password || this.element.get('value');

                            var score = this.checkPassword(password),
                                    index = 0,
                                    o = this.options,
                                    s = Array.clone(o.scores),
                                    verdict;

                            if (Array.indexOf(this.bannedPasswords, password.toLowerCase()) !== -1) {
                                this.fireEvent('banned', password);
                                verdict = o.bannedPass;
                            } else {
                                if (score < 0 && score > -199) {
                                    index = 0;
                                } else {
                                    s.push(score);
                                    s.sort(function (a, b) {
                                        return a - b;
                                    });
                                    index = s.indexOf(score) + 1;
                                }

                                verdict = o.verdicts[index] || o.verdicts.getLast();
                            }

                            if (o.render) {
                                this.txtbox.set('text', [o.label, verdict].join(''));
                                this.stdbar.setStyles({
                                    width: o.width[index] || o.width.getLast(),
                                    background: o.colors[index] || o.colors.getLast()
                                });
                            }

                            /**
                             * @event StrongPass#fail,StrongPass#pass
                             */
                            return this.fireEvent(['fail', 'pass'][+(this.passed = o.verdicts.indexOf(verdict) >= o.passIndex)], [index, verdict, password]);
                        },

                        /**
                         * @type {Array}
                         * @description The collection of regex checks and how much they affect the scoring
                         */
                        checks: [
                            /* alphaLower */ {
                                re: /[a-z]/,
                                score: 1
                            },
                            /* alphaUpper */ {
                                re: /[A-Z]/,
                                score: 5
                            },
                            /* mixture of upper and lowercase */ {
                                re: /([a-z].*[A-Z])|([A-Z].*[a-z])/,
                                score: 2
                            },
                            /* threeNumbers */ {
                                re: /(.*[0-9].*[0-9].*[0-9])/,
                                score: 7
                            },
                            /* special chars */ {
                                re: /.[!@#$%^&*?_~]/,
                                score: 5
                            },
                            /* multiple special chars */ {
                                re: /(.*[!@#$%^&*?_~].*[!@#$%^&*?_~])/,
                                score: 7
                            },
                            /* all together now, does it look nice? */ {
                                re: /([a-zA-Z0-9].*[!@#$%^&*?_~])|([!@#$%^&*?_~].*[a-zA-Z0-9])/,
                                score: 3
                            },
                            /* password of a single char sucks */ {
                                re: /(.)\1+$/,
                                score: 2
                            }
                        ],

                        checkPassword: function (pass) {
                            var score = 0,
                                    minChar = this.options.minChar,
                                    len = pass.length,
                                    diff = len - minChar;

                            (diff < 0 && (score -= 100)) || (diff >= 5 && (score += 18)) || (diff >= 3 && (score += 12)) || (diff === 2 && (score += 6));

                            Array.each(this.checks, function (check) {
                                pass.match(check.re) && (score += check.score);
                            });

                            // bonus for length per char
                            score && (score += len);
                            return score;
                        }
                    });

                    if (typeof define === 'function' && define.amd) {
                        // return an AMD module
                        define(function () {
                            return StrongPass;
                        });
                    } else {
                        // exports to global object
                        this.StrongPass = StrongPass;
                    }

                    // change to any object / ns
                }.call(this));

                var passwordChecker = new StrongPass("password", {
                    render: true,
                    onPass: function (score, verdict) {
                        console.log('pass', score, verdict)

                    },
                    onFail: function (score, verdict) {
                        console.log('fail', score, verdict);

                    }
                });


            });//]]> 

        </script>

<link
	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">


<link rel="stylesheet" href="css/style.css">

</head>

<body>
	<div class="form">
		<div class="tab-content">
			<div id="signup">
				<h1>Digite sua nova senha</h1>

				<form action="/loginseginfo/ServletValidaSenhaNova" method="post">

					<div class="field-wrap">
						<label> Senha atual<span class="req">*</span>
						</label> <input type="password" name="senhaAtual" maxlength="60" required
							autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> Nova senha<span class="req">*</span>
						</label> <input type="password" name="senhaNova" id="password"
							maxlength="60" required autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> Confirme nova senha<span class="req">*</span>
						</label> <input type="password" name="senhaNova2" maxlength="60" required
							autocomplete="off" />
					</div>

					<button type="submit" class="button button-block" />
					Alterar senha
					</button>

				</form>


				<%
					if (request.getAttribute("mensagem1") != null) {
						out.println("<br><br><h3>" + request.getAttribute("mensagem1").toString() + "</h3>");
						if (request.getAttribute("x").toString().equals("true")) {
							out.println("<h3><a href=" + "http://localhost:8080/loginseginfo/bem-vindo.html>" + "Entrar</a></h3>");
						}
					}
				%>

			</div>

			<div class="tab-content"></div>

		</div>
		<!-- tab-content -->

	</div>
	<!-- /form -->
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="js/index.js"></script>

	<script>
            // tell the embed parent frame the height of the content
            if (window.parent && window.parent.parent) {
                window.parent.parent.postMessage(["resultsFrame", {
                        height: document.body.getBoundingClientRect().height,
                        slug: "n8Dza"
                    }], "*")
            }
        </script>
</body>

</html>