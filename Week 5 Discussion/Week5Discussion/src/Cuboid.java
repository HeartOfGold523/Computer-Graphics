import com.jogamp.opengl.GL2;


class Cuboid {
  @SuppressWarnings("SameParameterValue")
  static void draw(GL2 gl, float size, int frameNumber) {
    cuboid(gl, (float) Math.sin(size * frameNumber / 50));
  }

 
  private static void cuboid(GL2 gl, float size){
    gl.glPushMatrix();
    gl.glScalef(size, size, size);

 
    drawRectangle(gl);

 
    gl.glPushMatrix();
    gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
    drawRectangle(gl);
    gl.glPopMatrix();

 
    gl.glPushMatrix();
    gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
    drawRectangle(gl);
    gl.glPopMatrix();

 
    gl.glPushMatrix();
    gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
    drawRectangle(gl);
    gl.glPopMatrix();

 
    gl.glPushMatrix();
    gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
    gl.glTranslatef(0f, 0f, 0.25f);
    drawSquare(gl);
    gl.glPopMatrix();

 
    gl.glPushMatrix();
    gl.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
    gl.glTranslatef(0f, 0f, 0.25f);
    drawSquare(gl);
    gl.glPopMatrix();

 
    gl.glPopMatrix();
  }

 
  static void drawSquare(GL2 gl){
    gl.glBegin(GL2.GL_TRIANGLE_FAN);

 
    gl.glColor3f(1.0f, 0.0f, 0.0f);
    gl.glVertex3f(-0.5f, -0.5f, 0.5f);
    gl.glColor3f(0.0f, 0.0f, 1.0f);
    gl.glVertex3f(0.5f, -0.5f, 0.5f);
    gl.glColor3f(0.0f, 1.0f, 0.0f);
    gl.glVertex3f(0.5f, 0.5f, 0.5f);
    gl.glColor3f(1.0f, 0.0f, 0.0f);
    gl.glVertex3f(-0.5f, 0.5f, 0.5f);

 
    gl.glEnd();
  }

 
  private static void drawRectangle(GL2 gl){
    gl.glColor3f(0.42f, 0.2f, 0.75f);
    gl.glBegin(GL2.GL_TRIANGLE_FAN);

 
    gl.glVertex3f(-0.75f, -0.5f, 0.5f);
    gl.glVertex3f(0.75f, -0.5f, 0.5f);
    gl.glVertex3f(0.75f, 0.5f, 0.5f);
    gl.glVertex3f(-0.75f, 0.5f, 0.5f);

 
    gl.glEnd();
  }
}