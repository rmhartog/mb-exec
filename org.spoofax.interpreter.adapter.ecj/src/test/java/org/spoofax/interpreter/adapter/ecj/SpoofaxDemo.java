/*
 * Created on 10. okt. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

//import org.eclipse.core.resources.IFile;
//import org.eclipse.core.resources.IProject;
//import org.eclipse.core.resources.IWorkspace;
//import org.eclipse.core.resources.IWorkspaceRoot;
//import org.eclipse.core.resources.ResourcesPlugin;
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.equinox.app.IApplication;
//import org.eclipse.equinox.app.IApplicationContext;
//import org.eclipse.jdt.core.ICompilationUnit;
//import org.eclipse.jdt.core.JavaCore;
//import org.eclipse.jdt.core.dom.AST;
//import org.eclipse.jdt.core.dom.ASTParser;
//import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
//import org.eclipse.jdt.core.dom.CompilationUnit;
//import org.eclipse.jdt.core.dom.ITypeBinding;
//
//public class SpoofaxDemo implements IApplication {
//
//    private void test() {
//        try {
//            IWorkspace workspace = ResourcesPlugin.getWorkspace();
//            IWorkspaceRoot root = workspace.getRoot();
//            IProject project  = root.getProject("demo1");
//            IFile file = project.getFile("HelloWorld.java");
//            if (!project.isOpen()) project.open(null);
//            ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
//            ASTParser parser = ASTParser.newParser(AST.JLS3);
//            parser.setResolveBindings(true);
//            parser.setSource(cu);
//            CompilationUnit n = (CompilationUnit)parser.createAST(null);
//            AbstractTypeDeclaration t = (AbstractTypeDeclaration) n.types().get(0);
//            ITypeBinding tb = t.resolveBinding();
//            System.out.print(n);
//            System.out.println(tb);
//        } catch(CoreException e) {
//            e.printStackTrace();
//        }
//    }
//    
//
//	public Object start(IApplicationContext context) throws Exception {
//        ResourcesPlugin p = ResourcesPlugin.getPlugin();
//        System.out.println(p);
//        System.out.println("Hello from Eclipse application");
//        test();
//        return EXIT_OK;
//	}
//
//	public void stop() {
//		// TODO Auto-generated method stub
//		
//	}
//
//    /*
//    private void test() {
//        try {
//            IWorkspace workspace = ResourcesPlugin.getWorkspace();
//            IWorkspaceRoot root = workspace.getRoot();
//            IProject project  = root.getProject("demo1");
//            IFolder folder = project.getFolder("src");
//            IFile file = project.getFile("HelloWorld.java");
//            //at this point, no resources have been created
//            if (!project.exists()) project.create(null);
//            if (!project.isOpen()) project.open(null);
//            if (!folder.exists()) 
//                folder.create(IResource.NONE, true, null);
//            
//            if (!file.exists()) {
//                byte[] bytes = "class X { }".getBytes();
//                InputStream source = new ByteArrayInputStream(bytes);
//                file.create(source, IResource.NONE, null);
//            }
//            
//            ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
//            ASTParser parser = ASTParser.newParser(AST.JLS3);
//            parser.setResolveBindings(true);
//            parser.setSource(cu);
//            CompilationUnit n = (CompilationUnit)parser.createAST(null);
//            AbstractTypeDeclaration t = (AbstractTypeDeclaration) n.types().get(0);
//            ITypeBinding tb = t.resolveBinding();
//            System.out.print(n);
//            System.out.println(tb);
//        } catch(CoreException e) {
//            e.printStackTrace();
//        }
//    }
//    */
//}
