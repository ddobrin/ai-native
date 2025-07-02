package com.example.ainative.config;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration
@ImportRuntimeHints(GoogleGenAiRuntimeHints.GoogleGenAiRuntimeHintsRegistrar.class)
public class GoogleGenAiRuntimeHints {

    public static class GoogleGenAiRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection()
                    .registerType(com.google.genai.Client.class, 
                            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                            MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                            MemberCategory.INVOKE_DECLARED_METHODS,
                            MemberCategory.INVOKE_PUBLIC_METHODS,
                            MemberCategory.DECLARED_FIELDS,
                            MemberCategory.PUBLIC_FIELDS)
                    .registerType(com.google.genai.ResponseStream.class,
                            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                            MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                            MemberCategory.INVOKE_DECLARED_METHODS,
                            MemberCategory.INVOKE_PUBLIC_METHODS,
                            MemberCategory.DECLARED_FIELDS,
                            MemberCategory.PUBLIC_FIELDS)
                    .registerType(com.google.genai.types.GenerateContentResponse.class,
                            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                            MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                            MemberCategory.INVOKE_DECLARED_METHODS,
                            MemberCategory.INVOKE_PUBLIC_METHODS,
                            MemberCategory.DECLARED_FIELDS,
                            MemberCategory.PUBLIC_FIELDS);
            
            hints.resources()
                    .registerPattern("com/google/genai/*");
        }
    }
}