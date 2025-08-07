import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  isSignUpMode = false;

  constructor(private router: Router) {}

  ngOnInit() {
    this.setupEventListeners();
  }

  setupEventListeners() {
    // Using Angular approach instead of direct DOM manipulation
    setTimeout(() => {
      const signUpButton = document.getElementById('signUp');
      const signInButton = document.getElementById('signIn');
      const container = document.getElementById('container');

      if (signUpButton && container) {
        signUpButton.addEventListener('click', () => {
          this.toggleSignUpMode(true);
        });
      }

      if (signInButton && container) {
        signInButton.addEventListener('click', () => {
          this.toggleSignUpMode(false);
        });
      }
    });
  }

  toggleSignUpMode(isSignUp: boolean) {
    this.isSignUpMode = isSignUp;
    const container = document.getElementById('container');
    if (container) {
      if (isSignUp) {
        container.classList.add('right-panel-active');
      } else {
        container.classList.remove('right-panel-active');
      }
    }
  }

  

  onSignUp(event: Event) {
    event.preventDefault();
    console.log('Sign Up clicked');
    // Add your sign-up logic here
  }
    onCreateAccount(form: NgForm) {
    if (form.valid) {
      alert('Account created successfully!');
      this.isSignUpMode = false; // ✅ Switch to login after successful signup
    } else {
      alert('Please fill all fields!');
    }
  }

  // Called when user clicks "Sign In" or "Sign Up" toggle buttons
  switchMode(signUp: boolean) {
    this.isSignUpMode = signUp;
  }
  onSignIn(form: NgForm) {
    if (form.valid) {
      alert('Login successful!');
      // Simulate login success and navigate
      this.router.navigate(['/TodoForm']);
    } else {
      alert('Please enter email and password!');
    }
  }
  

}
