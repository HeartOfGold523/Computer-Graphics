import com.jogamp.opengl.GL2;

//in this class I draw 6 distinct shapes 
public class Shapes
{
	//shape 1. this creates a 3D triangle with only edges drawn while the faces of the triangle are not filled in
	public static void drawShape1(GL2 gl2)
	{
		//begin drawing
		gl2.glBegin(GL2.GL_LINE_LOOP);
		
		//front side
		gl2.glColor3f( 1.0f, 0.0f, 0.0f );
		gl2.glVertex3f( 0f, 1.5f, 0.0f );
		gl2.glColor3f( 0.0f, 1.0f, 0.0f );
		gl2.glVertex3f( -.5f, -.5f, .5f );
		gl2.glColor3f( 0.0f, 0.0f, 1.0f );
		gl2.glVertex3f( .5f, -.5f, .5f );
		
		//right side
		gl2.glColor3f( 1.0f, 0.0f, 0.0f );
		gl2.glVertex3f( 0f, 1.5f, 0.0f );
		gl2.glColor3f( 0.0f, 0.0f, 1.0f );
		gl2.glVertex3f( .5f, -.5f, .5f );
		gl2.glColor3f( 0.0f, 1.0f, 0.0f );
		gl2.glVertex3f( .5f, -.5f, -.5f );
		
		//back side
		gl2.glColor3f( 1.0f, 0.0f, 0.0f );
		gl2.glVertex3f( 0f, 1.5f, 0.0f );
		gl2.glColor3f( 0.0f, 1.0f, 0.0f );
		gl2.glVertex3f( .5f, -.5f, -.5f );
		gl2.glColor3f( 0.0f, 0.0f, 1.0f );
		gl2.glVertex3f( -.5f, -.5f, -.5f );
		
		//left side
		gl2.glColor3f( 1.0f, 0.0f, 0.0f );
		gl2.glVertex3f( 0f, 1.5f, 0.0f );
		gl2.glColor3f( 0.0f, 0.0f, 1.0f );
		gl2.glVertex3f( -.5f, -.5f, -.5f );
		gl2.glColor3f( 0.0f, 1.0f, 0.0f );
		gl2.glVertex3f( -.5f, -.5f, .5f );
		
		//done drawing
		gl2.glEnd();
		gl2.glFlush();
	}
	
	//helper function for shape 2. this function creates squares for each of the 6 sides of our cube.
	//each square will be completely filled in with the 4 corners of each square a different color
	private static void shape2Helper(GL2 gl2)
	{
		//begin drawing. use GL_TRIANGLE_FAN to fill in square completely.
		gl2.glBegin(GL2.GL_TRIANGLE_FAN);
		
		//Bottom Left Corner = RED
		gl2.glColor3f( 1.0f, 0.0f, 0.0f );
		gl2.glVertex3d(-0.5, -0.5, 0.5);
		
		//Bottom Right Corner = GREEN
		gl2.glColor3f( 0.0f, 1.0f, 0.0f );
		gl2.glVertex3d(0.5, -0.5, 0.5);
		
		//Top Right Corner = BLUE
		gl2.glColor3f( 0.0f, 0.0f, 1.0f );
		gl2.glVertex3d(0.5, 0.5, 0.5);
		
		//Top Left Corner = YELLOW
		gl2.glColor3f( 1.0f, 1.0f, 0.0f );
		gl2.glVertex3d(-0.5, 0.5, 0.5);
		
		//done drawing
		gl2.glEnd();
		
		//outline edges of square in black to help see each face clearly
		gl2.glColor3d(0,0,0);
		gl2.glBegin(GL2.GL_LINE_LOOP);
		
		gl2.glVertex3d(-0.5, -0.5, 0.5);
		gl2.glVertex3d(0.5, -0.5, 0.5);
		gl2.glVertex3d(0.5, 0.5, 0.5);
		gl2.glVertex3d(-0.5, 0.5, 0.5);
		
		//done drawing
		gl2.glEnd();
	}
	
	//shape 2. this creates a cube using a helper method to create each of the 6 sides
	public static void drawShape2(GL2 gl2)
	{
		//front face
		gl2.glPushMatrix();
		shape2Helper(gl2);
		gl2.glPopMatrix();
		
		//right face
		gl2.glPushMatrix();
		gl2.glRotated(90, 0, 1, 0);
		shape2Helper(gl2);
		gl2.glPopMatrix();
		
		//top face
		gl2.glPushMatrix();
		gl2.glRotated(-90, 1, 0, 0);
		shape2Helper(gl2);
		gl2.glPopMatrix();
		
		//back face
		gl2.glPushMatrix();
		gl2.glRotated(180, 0, 1, 0);
		shape2Helper(gl2);
		gl2.glPopMatrix();
		
		//left face
		gl2.glPushMatrix();
		gl2.glRotated(-90, 0, 1, 0);
		shape2Helper(gl2);
		gl2.glPopMatrix();
		
		//bottom face
		gl2.glPushMatrix();
		gl2.glRotated(90, 1, 0, 0);
		shape2Helper(gl2);
		gl2.glPopMatrix();
		
		//done
		gl2.glFlush();
	}
	
	//shape 3. this creates a transparent 3D cylinder with radius, height, number of slices, number of stacks, and number of rings
	public static void drawShape3(GL2 gl2, double radius, double height, int slices, int stacks, int rings)
	{
		for (int i = 0; i < stacks; i++)
		{
			double z1 = (height / stacks) * i;
			double z2 = (height / stacks) * (i + 1);
			gl2.glBegin(GL2.GL_QUAD_STRIP);
			
			for (int j = 0; j <= slices; j++)
			{
				double angle = (2 * Math.PI / slices) * j;
				double y = Math.sin(angle);
				double x = Math.cos(angle);
				
				gl2.glNormal3d(x, y, 0);
				gl2.glColor4f(1.0f, 0.0f, 0.0f, 0.5f);
				gl2.glVertex3d(radius * x, radius * y, z2);
				gl2.glVertex3d(radius * x, radius * y, z1);
			}
			gl2.glEnd();
		}
		
		if (rings > 0)
		{
			gl2.glNormal3d(0, 0, 1);
			for (int i = 0; i < rings; i++)
			{
				double d1 = (1.0 / rings) * i;
				double d2 = (1.0 / rings) * (i + 1);
				gl2.glBegin(GL2.GL_QUAD_STRIP);
				
				for (int j = 0; j <= slices; j++)
				{
					double angle = (2 * Math.PI / slices) * j;
					double y = Math.sin(angle);
					double x = Math.cos(angle);
					
					gl2.glColor3f(0.0f, 0.0f, 1.0f);
					gl2.glVertex3d(radius * x * d1, radius * y * d1, height);
					
					gl2.glColor3f(0.0f, 1.0f, 0.0f);
					gl2.glVertex3d(radius * x * d2, radius * y * d2, height);
				}
				gl2.glEnd();
			}
			
			gl2.glNormal3d(0, 0, -1);
			for (int i = 0; i < rings; i++)
			{
				double d1 = (1.0 / rings) * i;
				double d2 = (1.0 / rings) * (i + 1);
				gl2.glBegin(GL2.GL_QUAD_STRIP);
				
				for (int j = 0; j <= slices; j++)
				{
					double angle = (2 * Math.PI /slices) * j;
					double y = Math.sin(angle);
					double x = Math.cos(angle);
					  
					gl2.glColor3f(0.0f, 0.0f, 1.0f);
					gl2.glVertex3d(radius * x * d2, radius * y * d2, 0);
					  
					gl2.glColor3f(0.0f, 1.0f, 0.0f);
					gl2.glVertex3d(radius * x * d1, radius * y * d1, 0);
				}
				gl2.glEnd();
			}
		}
		gl2.glFlush();
	}
	
	//shape 4. this creates a 3D '+' symbol using an Indexed Face Set
	public static void drawShape4(GL2 gl2)
	{
		//array of all vertices in + symbol, in no particular order
		double[][] vertices = new double[][]{
			{2, -2, 2},
			{2, -2, -2},
			{2, 1, -2},
			{2, 1, 2},
			{1.5, 5.0, 0},
			{-1.5, 5.0, 0},
			{-2, -2, 2},
			{-2, 1, 2},
			{-2, 1, -2},
			{-2, -2, -2},
			{1.5, -5.0, 0},
			{-1.5, -5.0, 0},
			{7, 1.5, 0},
			{7, -1.5, 0},
			{-7, 1.5, 0},
			{-7, -1.5, 0}};
		
		int[][] faces = new int[][] {
			{0, 1, 2, 3},
			{3, 2, 4},
			{7, 3, 4, 5},
			{2, 8, 5, 4},
			{5, 8, 7},
			{0, 3, 7, 6},
			{2, 1, 9, 8},
			{6, 7, 8, 9},
			{0, 1, 10},
			{6, 9, 11},
			{6, 0, 10, 11},
			{9, 1, 10, 11},
			{3, 0, 13, 12},
			{1, 2, 12, 13},
			{2, 3, 12},
			{0, 1, 13},
			{6, 7, 14, 15},
			{9, 8, 14, 15},
			{7, 8, 14},
			{6, 9, 15}};
		
		double[][] faceColors = new double[][] {
			{1, 0, 0},
			{1, 1, 0},
			{0, 0, 1},
			{0, 0, 1},
			{1, 1, 0},
			{0, 1, 0},
			{0, 1, 0},
			{1, 0, 0},
			{1, 1, 0},
			{1, 1, 0},
			{0, 0, 1},
			{0, 0, 1},
			{0, 0, 1},
			{0, 0, 1},
			{1, 0, 0},
			{1, 0, 0},
			{0, 0, 1},
			{0, 0, 1},
			{1, 0, 0},
			{1, 0, 0}};
		
		gl2.glPushMatrix();
		  
		for (int i = 0; i < faces.length; i++)
		{
			//gl2.glColor4d(0, 0, 0, 0.3);
			gl2.glColor4d(0, 1, 0, 0.3);
			gl2.glBegin(GL2.GL_TRIANGLE_FAN);
			
			for (int j = 0; j < faces[i].length; j++)
			{
				int index = faces[i][j];
				gl2.glVertex3dv(vertices[index], 0);
			}
			gl2.glEnd();
		}
		  
		//draw edges colored in black
		gl2.glColor3f(0, 0, 0);
		for (int i = 0; i < faces.length; i++)
		{
			gl2.glBegin(GL2.GL_LINE_LOOP);
			for (int j = 0; j < faces[i].length; j++)
			{
				gl2.glColor3dv(faceColors[j], 0);
				int index = faces[i][j];
				gl2.glVertex3dv(vertices[index], 0);
			}
			gl2.glEnd();
		}
		  
		gl2.glPopMatrix();
		gl2.glFlush();
	}
	
	//shape 5. this creates a 3D Tetrahedron using an Indexed Face Set
	public static void drawShape5(GL2 gl2)
	{
		//array of all the vertices of the tetrahedron, in no particular order.
		double[][] vertices = new double[][] {
			{1, -1, -1},
			{-1, -1, 1},
			{-1, 1, -1},
			{1, 1, 1}};
		
		int[][] faces = new int[][] {
			{0, 3, 2},
			{3, 0, 1},
			{0, 2, 1},
			{2, 3, 1}};
		
		double[][] faceColors = new double[][] {
			{1, 0, 0},
			{0, 1, 0},
			{0, 0, 1},
			{1, 1, 0}};
		
		gl2.glPushMatrix();
		
		//color the faces of the tetrahedron
		for (int i = 0; i < faces.length; i++)
		{
			gl2.glColor3dv(faceColors[i], 0 );
			gl2.glBegin(GL2.GL_TRIANGLE_FAN);
			
			for (int j = 0; j < faces[i].length; j++)
			{
				int index = faces[i][j];
				gl2.glVertex3dv(vertices[index], 0);
			}
			
			gl2.glEnd();
		}
		  
		//draw edges colored in black
		gl2.glColor3f(0,0,0);
		
		for (int i = 0; i < faces.length; i++)
		{
			gl2.glBegin(GL2.GL_LINE_LOOP);
			
			for (int j = 0; j < faces[i].length; j++)
			{
				int index = faces[i][j];
				gl2.glVertex3dv(vertices[index], 0);
			}
			
			gl2.glEnd();
		}
		
		gl2.glPopMatrix();
		gl2.glFlush();
	}
	
	//shape 6. this creates a transparent 3D Icosahedron using an Indexed Face Set
	public static void drawShape6(GL2 gl2)
	{
		//array of all the vertices of the Icosahedron, in no particular order
		double[][] vertices = new double[][] {
			{-1, -0.5, 0},
			{0, 1, 0.5},
			{0, 1, -0.5},
			{1, 0.5, 0},
			{1, -0.5, 0},
			{0, -1, -0.5},
			{0, -1, 0.5},
			{0.5, 0, 1},
			{-0.5, 0, 1},
			{0.5, 0, -1},
			{-0.5, 0, -1},
			{-1, 0.5, 0}};
		
		int[][] faces = new int[][] {
			{3, 7, 1},
			{4, 7, 3},
			{6, 7, 4},
			{8, 7, 6},
			{7, 8, 1},
			{9, 4, 3},
			{2, 9, 3},
			{2, 3, 1},
			{11, 2, 1},
			{10, 2, 11},
			{10, 9, 2},
			{9, 5, 4},
			{6, 4, 5},
			{0, 6, 5},
			{0, 11, 8},
			{11, 1, 8},
			{10, 0, 5},
			{10, 5, 9},
			{0, 8, 6},
			{0, 10, 11}};
		
		//fourth column per row indicates transparency
		double[][] faceColors = new double[][] {
			{1, 0, 0, 0.4},
			{0, 1, 0, 0.4},
			{0, 0, 1, 0.4},
			{1, 1, 0, 0.4},
			{0, 1, 1, 0.4},
			{1, 0, 0, 0.4},
			{0, 1, 0, 0.4},
			{0, 0, 1, 0.4},
			{1, 1, 0, 0.4},
			{0, 1, 1, 0.4},
			{1, 0, 0, 0.4},
			{0, 1, 0, 0.4},
			{0, 0, 1, 0.4},
			{1, 1, 0, 0.4},
			{0, 1, 1, 0.4},
			{1, 0, 0, 0.4},
			{0, 1, 0, 0.4},
			{0, 0, 1, 0.4},
			{1, 1, 0, 0.4},
			{0, 1, 1, 0.4},
			{0, 0, 1, 0.4}};
		
		gl2.glPushMatrix();
		//color the faces of the Icosahedron
		for (int i = 0; i < faces.length; i++)
		{
			gl2.glColor4dv(faceColors[i], 0 );
			gl2.glBegin(GL2.GL_TRIANGLE_FAN);
			
			for (int j = 0; j < faces[i].length; j++)
			{
				int index = faces[i][j];
				gl2.glVertex3dv(vertices[index], 0);
			}
			
			gl2.glEnd();
		}
		  
		//draw edges colored in black
		gl2.glColor3f(0, 0, 0);
		for (int i = 0; i < faces.length; i++)
		{
			gl2.glBegin(GL2.GL_LINE_LOOP);
			
			for (int j = 0; j < faces[i].length; j++)
			{
				int index = faces[i][j];
				gl2.glVertex3dv(vertices[index], 0);
			}
			gl2.glEnd();
		}
		  
		gl2.glPopMatrix();
		gl2.glFlush();
	}
}
