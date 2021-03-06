/*
 * Copyright 2014 Bern University of Applied Sciences.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.fusepool.p3.transformer.server;

import eu.fusepool.p3.transformer.server.handler.TransformerFactoryHandler;
import eu.fusepool.p3.transformer.Transformer;
import eu.fusepool.p3.transformer.TransformerFactory;
import eu.fusepool.p3.transformer.server.handler.TransformerHandlerFactory;
import org.eclipse.jetty.server.Server;

/**
 *
 * @author reto
 */
public class TransformerServer {

    private final Server server;

    public TransformerServer(int port) {
        server = new Server(port);
    }
    
    /**
     * 
     * @param transformer
     * @throws Exception ugly, but so does the underlying Jetty Server
     */
    public void start(Transformer transformer) throws Exception {
        server.setHandler(TransformerHandlerFactory.getTransformerHandler(transformer));
        server.start();
    }
    
    public void start(TransformerFactory factory) throws Exception {
        server.setHandler(new TransformerFactoryHandler(factory));
        server.start();   
    }
    
    public void join() throws InterruptedException {
        server.join();
    }
}
